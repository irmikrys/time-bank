import React, { Component } from 'react';
import Dropzone from "react-dropzone";
import {
  USERNAME,
  FIRST_NAME,
  LAST_NAME,
  EMAIL,
  LOCATION,
  PASSWORD,
  getFormField,
} from "../../constants/constants";
import Geosuggest from "react-geosuggest";
import axios from 'axios';

export default class EditProfileModal extends Component {

  constructor(props) {
    super(props);
    const {user, location} = props.user;
    this.state = {
      [USERNAME]: user.username,
      [FIRST_NAME]: user.firstName,
      [LAST_NAME]:  user.lastName,
      [EMAIL]:  user.email,
      [LOCATION]: location,
      [PASSWORD]: ""
    };
  }

  componentDidMount(){
    const {photo} = this.props.user.user;
    if(photo) {
      const img = document.getElementById('avatar-edit');
      img.src = `data:image/png;base64,${photo}`;
    }
  }

  handleInputChange = event => {
    let value = event.target.value;
    let inputName = event.target.name;
    this.setState({[inputName]: value});
  };

  handleLocationSelect = value => {
    const location = {
      description : value.label,
      latitude : value.location.lat,
      longitude: value.location.lng
    };
    this.setState({location: location})
  };

  onDrop = files => {
    let data = new FormData();
    data.append('file', files[0]);
    axios.put('/api/updateUserProfilePhoto', data)
      .then(result => {
        const img = document.getElementById('avatar-edit');
        img.src = files[0].preview;
      });
  };

  handleSubmit = event => {
    event.preventDefault();
    const { editProfile, closeModal, fetchUser } = this.props;
    editProfile(this.state, closeModal, fetchUser);
  };

  render() {
    const {location} = this.props.user;
    return (
      <div>
        <div className="container edit-modal">
          <div className="details">
            <div className="paragraph">
              <div className="paragraph-title">
                <h3>Edit Profile</h3>
                <span onClick={this.props.closeModal} className="remove-icon glyphicon glyphicon-remove"/>
                <div className="profile-view">
                  <div className="column">
                    <div className="drop-down-zone">
                      <Dropzone onDrop={this.onDrop.bind(this)}
                                multiple={false}
                      >
                        <img id="avatar-edit"
                             src={require("avatar.png")}
                        />
                      </Dropzone>
                    </div>
                  </div>
                  <div className="column">
                    <div className="edit-form">
                      {
                        [FIRST_NAME, LAST_NAME, EMAIL].map(fieldKey => {
                          const field = getFormField(fieldKey);
                          return <input key={field.name}
                                        value={this.state[fieldKey]}
                                        type={field.type}
                                        placeholder={field.placeholder}
                                        name={field.name}
                                        pattern={field.pattern}
                                        onChange={this.handleInputChange}
                                        required
                          />
                        })
                      }
                      <Geosuggest placeholder={LOCATION}
                                  initialValue={location.description}
                                  onSuggestSelect={this.handleLocationSelect}
                      />
                      <input type="password"
                             placeholder="password"
                             name={PASSWORD}
                             pattern={getFormField(PASSWORD).pattern}
                             onChange={this.handleInputChange}
                             required
                      />
                      <button type="button" onClick={this.handleSubmit.bind(this)}>Save</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div id="cover"/>
      </div>
    )};

}
