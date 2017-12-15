import React, {Component} from 'react';
import {connect} from 'react-redux';

export class UserProfile extends Component {

  render() {
    return (
      <div>
        <h1>Hello, {this.props.username}!</h1>
      </div>
    )
  }
}

export default connect(
  ({authentication}) => ({username: authentication.username})
)(UserProfile);
