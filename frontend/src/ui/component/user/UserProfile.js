import React, {Component} from 'react';
import Dropzone from "react-dropzone";
import axios from 'axios';
import {Link} from "react-router";
import { browserHistory } from 'react-router';
import {advertDate, advertDetails, categoryToGlyph, transactionHeader} from "../../constants/constants";

export class UserProfile extends Component {

  componentDidMount(){
    const {photo} = this.props.user.user;
    if(photo) {
      const img = document.getElementById('avatar');
      img.src = `data:image/png;base64,${photo}`;
    }
  }

  onDrop = files => {
    let data = new FormData();
    data.append('file', files[0]);
    axios.put('/api/updateUserProfilePhoto', data)
      .then(result => {
        const img = document.getElementById('avatar');
        img.src = files[0].preview;
      });
  };

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
                    <td id="date">{advertDate(advert)}</td>
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

  openEditForm() {
    browserHistory.push("/edit_profile");
  }

  render() {
    console.log(this.props);
    const {createdAdverts, interestingAdverts, archivedAdverts} = this.props;
    const {user, location, account} = this.props.user;
    return (
      <div>
        <div className="container">
          <div className="details">
            <div className="paragraph">
              <div className="paragraph-title">
                <h3>Personal Data</h3>
                <span onClick={this.openEditForm.bind(this)} className="edit-icon glyphicon glyphicon-pencil"/>
              </div>
              <div className="profile-view">
                <div className="column">
                  <div className="drop-down-zone">
                    <Dropzone onDrop={this.onDrop.bind(this)}
                              multiple={false}
                    >
                      <img id="avatar"
                           src={require("avatar.png")}
                      />
                    </Dropzone>
                  </div>
                </div>
                <div className="column">
                  <table>
                    <tbody>
                    <tr>
                      <th>Username:</th>
                      <td>{user.username}</td>
                    </tr>
                    <tr>
                      <th>First Name:</th>
                      <td>{user.firstName}</td>
                    </tr>
                    <tr>
                      <th>Last Name:</th>
                      <td>{user.lastName}</td>
                    </tr>
                    <tr>
                      <th>Email:</th>
                      <td>{user.email}</td>
                    </tr>
                    <tr>
                      <th>Location:</th>
                      <td>{location.description}</td>
                    </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
            <div className="paragraph">
              <h3>Account</h3>
              <div>
                <label>Account Balance: </label>
                <span> {account.amount}</span>
              </div>
            </div>
            <div className="paragraph">
              <h3>Transactions</h3>
              <div>
                <label>Adverts I'm interested in:</label>
                <div>
                  {transactionHeader()}
                  {this.showAdverts(interestingAdverts)}
                </div>
                <label>My adverts:</label>
                <div>
                  {this.showAdverts(createdAdverts)}
                </div>
                <label>Archived adverts:</label>
                <div>
                  {this.showAdverts(archivedAdverts)}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

