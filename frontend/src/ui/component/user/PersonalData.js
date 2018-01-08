import React, {Component} from 'react';

export default class PersonalData extends Component {

  render() {
    const {user, location} = this.props.userInfo;
    return (
      <div className="paragraph">
        <div className="paragraph-title">
          <h3>Personal Data</h3>
          <span onClick={this.props.openModal} className="edit-icon glyphicon glyphicon-pencil"/>
        </div>
        <div className="profile-view">
          <div className="column">
            <div className="avatar-container">
              <img id="avatar" src={require("avatar.png")}/>
            </div>
          </div>
          <div className="column">
            <table>
              <tbody>
              <tr>
                <th>Username:</th>
                <td>{user.username}</td>
              </tr>
              <tr>
                <th>First Name:</th>
                <td>{user.firstName}</td>
              </tr>
              <tr>
                <th>Last Name:</th>
                <td>{user.lastName}</td>
              </tr>
              <tr>
                <th>Email:</th>
                <td>{user.email}</td>
              </tr>
              <tr>
                <th>Location:</th>
                <td>{location.description}</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    )
  }
}
