import React, {Component} from 'react';
import {connect} from 'react-redux';

export class UserProfile extends Component {

  render() {
    return (
      <div>
        <h1>My Profile</h1>
        <div className="container">
          <div className="avatar-field">
            <img id="myAvatar"
                 src="http://eoclimlab.eu/wp-content/uploads/2017/01/default.png"
            />
            <button type="button">Change avatar</button>
          </div>
          <p>Hello {this.props.username}!</p>
        </div>
      </div>
    )
  }
}

export default connect(
  ({authentication}) => ({username: authentication.username})
)(UserProfile);
