import React, {Component} from 'react';
import {connect} from 'react-redux';
import {UserProfile} from "../component/UserProfile";
import {fetchUserByUsername} from "../../reducers/user";

export class UserPage extends Component {

  constructor(props) {
    super(props);
    props.fetchUserByUsername(props.params.username);
  }

  render() {
    return (
      <div className="main">
        <h1>My Profile</h1>
        {
          !this.props.updating ? <UserProfile user={this.props.user} fetchUser={fetchUserByUsername}/> : <div className="loader"/>
        }
      </div>
    )
  }
}

export default connect(
  state => ({
    user: state.user.user,
    updating: state.user.updating
  }),
  {fetchUserByUsername}
)(UserPage);
