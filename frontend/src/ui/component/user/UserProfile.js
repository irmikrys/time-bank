import React, {Component} from 'react';
import Dropzone from "react-dropzone";
import axios from 'axios';
import {Link} from "react-router";

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
                <Link to={`/advert/${advert.idAdvert}`} key={advert.idAdvert}>
                  {advert.title}
                </Link>
              </div>
            )
          })
        }
      </div>
    )
  };

  render() {
    const {createdAdverts, interestingAdverts} = this.props;
    const {user, location, account} = this.props.user;
    console.log(this.props);
    return (
      <div>
        <div className="container">
          <div className="details">
            <div className="paragraph">
              <h3>Personal Data</h3>
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
                  <label>First Name:</label>
                  <div>{user.firstName}</div>
                  <label className="margin-top-2">Last Name:</label>
                  <div>{user.lastName}</div>
                  <label className="margin-top-2">Username:</label>
                  <div>{user.username}</div>
                  <label className="margin-top-2">Email:</label>
                  <div>{user.email}</div>
                  <label className="margin-top-2">Location:</label>
                  <div>{location.description}</div>
                  <label className="margin-top-2">Account Balance:</label>
                  <div>{account.amount}</div>
                </div>
              </div>
            </div>
            <div className="paragraph">
              <h3>Transactions</h3>
              <div className="profile-view">
                <label>Adverts I'm interested in:</label>
                <div>
                  {this.showAdverts(interestingAdverts)}
                </div>
                <label>My adverts:</label>
                <div>
                  {this.showAdverts(createdAdverts)}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

