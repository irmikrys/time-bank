import React, { Component } from 'react';
import {ErrorPanel} from "./ErrorPanel";
import {LabeledInput} from "./LabeledInput";

export default class LoginForm extends Component {

  state = {
    firstName: "",
    lastName:  "",
    email:  "",
    username: "",
    password: ""
  };

  handleInputChange = event => {
    let value = event.target.value;
    let inputName = event.target.name;
    this.setState({[inputName]: value});
  };

  render() {
    const {errorMessage} = this.props;
    const errorPanel = errorMessage ? <ErrorPanel messageKey={errorMessage}/> : null;
    return (
      <div>

        {errorPanel}

        <form onSubmit={this.handleSubmit}>
          <LabeledInput onChange={this.handleInputChange} label="First Name" name="firstName"/>
          <LabeledInput onChange={this.handleInputChange} label="Last Name" name="lastName"/>
          <LabeledInput onChange={this.handleInputChange} label="Email" name="email" type="email"/>
          <LabeledInput onChange={this.handleInputChange} label="Login" name="username"/>
          <LabeledInput onChange={this.handleInputChange} label="Password" name="password" type="password"/>
          <div>
            <button type="submit">Register</button>
          </div>
        </form>
      </div>
    );
  }

  handleSubmit = event => {
    event.preventDefault();
    const { register } = this.props;
    register(this.state);
  }
}
