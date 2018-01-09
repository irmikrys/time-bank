import React, {Component} from 'react';
import {Link} from "react-router";
import {categoryToGlyph} from "../../constants/constants";
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

  advertDate = advert => !advert.closeDate ? advert.createDate : advert.closeDate;

  advertDetails = advert => {
    return (
      !advert.closeDate ?
        <Link to={`/advert/${advert.idAdvert}`} key={advert.idAdvert}>
          <span className="glyphicon glyphicon-link"/>
        </Link>
        :
        this.accountDiff(advert)
    )
  };

  accountDiff = advert => {
    if(advert.owner === this.props.user.user.username) {
      console.log();
      return (
        advert.type === 'OFFER' ?
          `+${advert.value}h` :
          `-${advert.value}h`
      )
    } else {
      return (
        advert.type === 'OFFER' ?
          `-${advert.value}h` :
          `+${advert.value}h`
      )
    }
  };

  advertUser = (advert, TYPE) => {
    switch (TYPE) {
      case MY_ADVERTS:
        return (
          advert.contractor === null ? '-' : advert.contractor
        ); break;
      case INTERESTING:
        return (
          advert.owner
        ); break;
      case ARCHIVED:
        return (
          this.props.user.user.username === advert.owner ?
            advert.contractor :
            advert.owner
        );
      default:
        return (
          advert.title
        )
    }
  };

   transactionHeader = TYPE => (
     <table>
       <tbody>
       <tr>
         <th id="glyph"/>
         <th id="date">Date</th>
         <th id="type">Type</th>
         <th id="title">Title</th>
         <th id="user">User</th>
         <th id="details">Details</th>
       </tr>
       </tbody>
     </table>
   );

  showAdverts = (adverts, TYPE) => {
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
                    <td id="date">{dateFormatter(new Date(this.advertDate(advert)))}</td>
                    <td id="type">{advert.type}</td>
                    <td id="title">{advert.title}</td>
                    <td id="user">{this.advertUser(advert, TYPE)}</td>
                    <td id="details" className="big-font">{this.advertDetails(advert)}</td>
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

  openEditModal = () => {
    this.setState({editModalVisible: true});
  };

  closeEditModal = () => {
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
                    {this.transactionHeader(MY_ADVERTS)}
                    {this.showAdverts(createdAdverts, MY_ADVERTS)}
                  </div>
                  : null
              }
              {
                this.state.activeTab === INTERESTING ?
                  <div className="transaction">
                    {this.transactionHeader(INTERESTING)}
                    {this.showAdverts(interestingAdverts, INTERESTING)}
                    </div>
                  : null
              }
              {
                this.state.activeTab === ARCHIVED ?
                  <div className="transaction">
                    {this.transactionHeader(ARCHIVED)}
                    {this.showAdverts(archivedAdverts, ARCHIVED)}
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

