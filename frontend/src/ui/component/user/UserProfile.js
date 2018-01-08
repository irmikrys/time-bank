import React, {Component} from 'react';
import {Link} from "react-router";
import { browserHistory } from 'react-router';
import {advertDate, advertDetails, categoryToGlyph, transactionHeader} from "../../constants/constants";
import {Tabs} from "./Tabs";
import PersonalData from "./PersonalData";
import {ARCHIVED, INTERESTING, MY_ADVERTS} from "../../constants/constants";
import EditProfileModal from "./EditProfileModal";
import {dateFormatter} from "../utils";

export class UserProfile extends Component {

  state = {
    tabData: [
      {name: MY_ADVERTS, isActive: true},
      {name: INTERESTING, isActive: false},
      {name: ARCHIVED, isActive: false}
    ],
    activeTab: MY_ADVERTS,
    editModalVisible: false
  };

  componentDidMount(){
    const {photo} = this.props.user.user;
    if(photo) {
      const img = document.getElementById('avatar');
      img.src = `data:image/png;base64,${photo}`;
    }
  }

  showAdverts = adverts => {
    return (
      <div>
        {
          adverts.map(advert => {
            return (
              <div>
                <table>
                  <tbody>
                  <tr>
                    <th id="glyph"><span className={categoryToGlyph(advert.idCategory)}/></th>
                    <td id="date">{dateFormatter(new Date(advertDate(advert)))}</td>
                    <td id="type">{advert.type}</td>
                    <td id="title">{advert.title}</td>
                    <td id="details">{advertDetails(advert)}</td>
                  </tr>
                  </tbody>
                </table>
              </div>
            )
          })
        }
      </div>
    )
  };

  handleClick = activeTab => {
    this.setState({activeTab});
  };

  openEditModal() {
    this.setState({editModalVisible: true});
  };

  closeEditModal() {
    this.setState({editModalVisible: false});
  };

  render() {
    console.log(this.props);
    const {createdAdverts, interestingAdverts, archivedAdverts} = this.props;
    const {account} = this.props.user;
    return (
      <div>
        <div className="container">
          <div className="details">
            <PersonalData userInfo={this.props.user} openModal={this.openEditModal.bind(this)}/>
            <div className="paragraph">
              <h3>Account</h3>
              <div>
                <label>Account Balance: </label>
                <span> {account.amount}</span>
              </div>
            </div>
            <div className="paragraph">
              <Tabs tabData={this.state.tabData} activeTab={this.state.activeTab} changeTab={this.handleClick} />
              {
                this.state.activeTab === MY_ADVERTS ?
                  <div className="transaction">
                    {transactionHeader()}
                    {this.showAdverts(createdAdverts)}
                  </div>
                  : null
              }
              {
                this.state.activeTab === INTERESTING ?
                  <div className="transaction">
                    {transactionHeader()}
                    {this.showAdverts(interestingAdverts)}
                    </div>
                  : null
              }
              {
                this.state.activeTab === ARCHIVED ?
                  <div className="transaction">
                    {transactionHeader()}
                    {this.showAdverts(archivedAdverts)}
                    </div>
                  : null
              }
            </div>
          </div>
        </div>
        {this.state.editModalVisible ? <EditProfileModal editProfile={this.props.editProfile}
                                                         user={this.props.user}
                                                         closeModal={this.closeEditModal.bind(this)}
                                                         fetchUser={this.props.fetchUser}
        /> : null}
      </div>
    )
  }
}

