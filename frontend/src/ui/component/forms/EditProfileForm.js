import React, { Component } from 'react';
import {ErrorPanel} from "./ErrorPanel";
import {
  FIRST_NAME,
  LAST_NAME,
  EMAIL,
  LOCATION,
  getFormField
} from "../../constants/constants";
import Geosuggest from "react-geosuggest";
import {Link} from "react-router";

export default class EditProfileForm extends Component {

  state = {
    [FIRST_NAME]: "",
    [LAST_NAME]:  "",
    [EMAIL]:  "",
    [LOCATION]: {}
  };

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
    console.log(this.props);
    const {errorMessage} = this.props;
    const errorPanel = errorMessage ? <ErrorPanel messageKey={errorMessage}/> : null;
    return (
      <div className="register-page">
        <div className="form-container">
          <form autoComplete="off" onSubmit={this.handleSubmit}>
            {errorPanel}
            {
              [FIRST_NAME, LAST_NAME, EMAIL].map(fieldKey => {
                const field = getFormField(fieldKey);
                return <input key={field.name}
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
                        onSuggestSelect={this.handleLocationSelect}
            />
            <Link to={`/profile/${this.props.params.username}`}>
              <button type="submit">SAVE</button>
            </Link>
          </form>
        </div>
      </div>
    );
  }

  handleSubmit = event => {
    event.preventDefault();
    //const { editProfile } = this.props;
    //editProfile(this.state);
  }
}
