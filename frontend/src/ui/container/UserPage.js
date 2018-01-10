import React, {Component} from 'react';
import {connect} from 'react-redux';
import {UserProfile} from "../component/user/UserProfile";
import {fetchUserByUsername} from "../../reducers/user";
import {fetchCreatedAdverts} from "../../reducers/createdAdverts";
import {fetchInterestingAdverts} from "../../reducers/interestingAdverts";
import {fetchArchivedAdverts} from "../../reducers/archivedAdverts";
import {editProfile} from "../../reducers/editProfile";

export class UserPage extends Component {

  constructor(props) {
    super(props);
    props.fetchUserByUsername(props.username);
    props.fetchCreatedAdverts();
    props.fetchInterestingAdverts();
    props.fetchArchivedAdverts();
  }

  render() {
    return (
      <div className="main">
        <h1>My Profile</h1>
        {
          this.props.updatingUser ||
          this.props.updatingCreatedAdverts ||
          this.props.updatingInterestingAdverts ||
          this.props.updatingArchivedAdverts
            ?
            <div className="loader"/> :
            <UserProfile user={this.props.user}
                         createdAdverts={this.props.createdAdverts}
                         interestingAdverts={this.props.interestingAdverts}
                         archivedAdverts={this.props.archivedAdverts}
                         editProfile={this.props.editProfile}
                         fetchUser={this.props.fetchUserByUsername.bind(this, this.props.username)}
            />
        }
      </div>
    )
  }
}

export default connect(
  state => ({
    username: state.authentication.username,
    user: state.user.user,
    createdAdverts: state.createdAdverts.adverts,
    interestingAdverts: state.interestingAdverts.adverts,
    archivedAdverts: state.archivedAdverts.adverts,
    updatingUser: state.user.updating,
    updatingCreatedAdverts: state.createdAdverts.updating,
    updatingInterestingAdverts: state.interestingAdverts.updating,
    updatingArchivedAdverts: state.archivedAdverts.updating
  }),
  {fetchUserByUsername, fetchCreatedAdverts, fetchInterestingAdverts, fetchArchivedAdverts, editProfile}
)(UserPage);
