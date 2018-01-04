import React, {Component} from 'react';
import EditProfileForm from '../component/forms/EditProfileForm';
import {connect} from 'react-redux';
import {editProfile} from '../../reducers/editProfile';
import {fetchUserByUsername} from "../../reducers/user";

export class EditProfilePage extends Component {

  constructor(props) {
    super(props);
    props.fetchUserByUsername(props.username);
  }

  render() {
    console.log(this.props);
    return (
      <div className="main">
        <h1>Edit Profile</h1>
        {
          this.props.updatingUser ?
            <div className="loader"/> :
            <EditProfileForm user={this.props.user} editProfile={this.props.editProfile}/>
        }
      </div>
    )
  }
}

export default connect(
  state => ({
    user: state.user.user,
    updatingUser: state.user.updating,
    username: state.authentication.username
  }),
  {editProfile, fetchUserByUsername}
)(EditProfilePage);
