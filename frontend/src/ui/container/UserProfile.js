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
                 src={require("avatar.png")}
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
  state => ({username: state.authentication.username})
)(UserProfile);
