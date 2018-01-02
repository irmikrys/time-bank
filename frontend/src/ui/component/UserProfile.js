import React, {Component} from 'react';

export class UserProfile extends Component {

  render() {
    const {user, location, account} = this.props.user;
    console.log(this.props);
    return (
      <div>
        <div className="profile-container container">
          <div className="avatar-field">
            <img id="myAvatar"
                 src={require("avatar.png")}
            />
            <button type="button">CHANGE AVATAR</button>
          </div>
          <div className="data-div">
            <div className="paragraph">
              <h3>Personal Data</h3>
              <div>
                <label>First Name: </label>
                <div>{user.firstName}</div>
                <label>Last Name: </label>
                <div>{user.lastName}</div>
                <label>Username:</label>
                <div>{user.username}</div>
                <label>Email:</label>
                <div>{user.email}</div>
                <label>Location:</label>
                <div>{location.description}</div>
              </div>
            </div>
            <div className="paragraph">
              <h3>Account</h3>
              <div>
                <label>Account Balance: </label>
                <div>{account.amount}</div>
              </div>
            </div>
            <div className="paragraph">
              <h3>Transactions</h3>
              <div>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

