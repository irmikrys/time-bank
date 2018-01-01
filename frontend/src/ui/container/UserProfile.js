import React, {Component} from 'react';

export class UserProfile extends Component {

  render() {
    const {user} = this.props;
    console.log(this.props);
    return (
      <div>
        <div className="container">
          <div className="avatar-field">
            <img id="myAvatar"
                 src="http://eoclimlab.eu/wp-content/uploads/2017/01/default.png"
            />
            <button type="button">CHANGE AVATAR</button>
          </div>
          <div className="data-div">
            <div className="paragraph">
              <h3>Personal Data</h3>
              <div>
                <label>First name: </label>
                <div>{user.firstName}</div>
                <label>Last name: </label>
                <div>{user.lastName}</div>
                <label>username:</label>
                <div>{user.username}</div>
              </div>
            </div>
            <div className="paragraph">
              <h3>Account</h3>
              <div>
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

