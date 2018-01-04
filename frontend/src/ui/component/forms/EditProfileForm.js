import React, { Component } from 'react';
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

export default class EditProfileForm extends Component {

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

  render() {
    const {user, location} = this.props.user;
    return (
      <div className="register-page">
        <div className="form-container">
          <form autoComplete="off" onSubmit={this.handleSubmit}>
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
                        location={new google.maps.LatLng(location.latitude, location.longitude)}
                        onSuggestSelect={this.handleLocationSelect}
            />
            <input type="password"
                   placeholder="password"
                   name={PASSWORD}
                   pattern={getFormField(PASSWORD).pattern}
                   onChange={this.handleInputChange}
                   required
            />
            <button type="submit">SAVE</button>
          </form>
        </div>
      </div>
    );
  }

  handleSubmit = event => {
    event.preventDefault();
    const { editProfile } = this.props;
    editProfile(this.state);
  }
}
