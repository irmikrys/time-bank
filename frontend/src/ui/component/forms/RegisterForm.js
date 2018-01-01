import React, { Component } from 'react';
import {ErrorPanel} from "./ErrorPanel";
import {
  FIRST_NAME,
  LAST_NAME,
  EMAIL,
  USERNAME,
  PASSWORD,
  LOCATION,
  getFormField
} from "../../constants/constants";
import Geosuggest from "react-geosuggest";
import {Link} from "react-router";
import Checkbox from "./Checkbox";

export default class RegisterForm extends Component {

  state = {
    [FIRST_NAME]: "",
    [LAST_NAME]:  "",
    [EMAIL]:  "",
    [USERNAME]: "",
    [PASSWORD]: "",
    [LOCATION]: {},
    termsAccepted: false
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

  toggleCheckbox = () => {
    this.state.termsAccepted = !this.state.termsAccepted;
  };

  render() {
    const {errorMessage} = this.props;
    const errorPanel = errorMessage ? <ErrorPanel messageKey={errorMessage}/> : null;
    return (
      <div className="register-page">
        <div className="form-container">
          <form autoComplete="off" onSubmit={this.handleSubmit}>
            {errorPanel}
            {
              [FIRST_NAME, LAST_NAME, EMAIL, USERNAME, PASSWORD].map(fieldKey => {
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
            <Checkbox
              label={
                <div className="checkbox-label">
                  <span>I accept the </span>
                  <Link to={'/terms'} target="_blank">Terms of Use</Link>
                </div>
              }
              handleCheckboxChange={this.toggleCheckbox}
            />
            <button type="submit">Register</button>
          </form>
        </div>
      </div>
    );
  }

  handleSubmit = event => {
    event.preventDefault();
    if(this.state.termsAccepted){
      const { register } = this.props;
      register(this.state);
    }
    else {
      console.log('You have to accept terms first.') //todo: move to error label
    }
  }
}
