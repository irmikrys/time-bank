import React, {Component} from 'react';
import Dropzone from "react-dropzone";
import axios from 'axios';

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
    axios.put('/api/uploadProfilePhoto', data)
      .then(result => {
        const img = document.getElementById('avatar');
        img.src = files[0].preview;
      });
  };

  render() {
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
                  <table>
                    <tr>
                      <th>First Name:</th>
                      <td>{user.firstName}</td>
                    </tr>
                    <tr>
                      <th>Last Name:</th>
                      <td>{user.lastName}</td>
                    </tr>
                    <tr>
                      <th>Username:</th>
                      <td>{user.username}</td>
                    </tr>
                    <tr>
                      <th>Email:</th>
                      <td>{user.email}</td>
                    </tr>
                    <tr>
                      <th>Location:</th>
                      <td>{location.description}</td>
                    </tr>
                  </table>
                  <button id="editProfile" type="button">Edit</button>
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

