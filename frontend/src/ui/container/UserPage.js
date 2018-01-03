import React, {Component} from 'react';
import {connect} from 'react-redux';
import {UserProfile} from "../component/user/UserProfile";
import {fetchUserByUsername} from "../../reducers/user";
import {fetchCreatedAdverts} from "../../reducers/createdAdverts";
import {fetchInterestingAdverts} from "../../reducers/interestingAdverts";

export class UserPage extends Component {

  constructor(props) {
    super(props);
    props.fetchUserByUsername(props.params.username);
    props.fetchCreatedAdverts();
    props.fetchInterestingAdverts();
  }

  render() {
    return (
      <div className="main">
        <h1>My Profile</h1>
        {
          this.props.updatingUser || this.props.updatingCreatedAdverts || this.props.updatingInterestingAdverts?
            <div className="loader"/> :
            <UserProfile user={this.props.user}
                         createdAdverts={this.props.createdAdverts}
                         interestingAdverts={this.props.interestingAdverts}
            />
        }
      </div>
    )
  }
}

export default connect(
  state => ({
    user: state.user.user,
    createdAdverts: state.createdAdverts.adverts,
    interestingAdverts: state.interestingAdverts.adverts,
    updatingUser: state.user.updating,
    updatingCreatedAdverts: state.createdAdverts.updating,
    updatingInterestingAdverts: state.interestingAdverts.updating,
  }),
  {fetchUserByUsername, fetchCreatedAdverts, fetchInterestingAdverts}
)(UserPage);
