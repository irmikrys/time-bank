import React, { Component } from 'react';

const LabeledInput = (props) => (
  <div className="pure-control-group">
    <label>
      {props.label}
    </label>
    <input {...props}/>
  </div>
);

const ErrorPanel = ({messageKey}) => (
  <p className="error-panel">
    {messageKey}
  </p>
);

export default class LoginForm extends Component {

  state = {
    username: "",
    password: ""
  };

  handleInputChange = (e) => {
    let value = e.target.value;
    let inputName = e.target.name;
    this.setState({[inputName]: value});
  };

  render() {
    const {errorMessage} = this.props;
    const errorPanel = errorMessage ? <ErrorPanel messageKey={errorMessage}/> : null;
    return (
      <div>

        {errorPanel}

        <form onSubmit={this.handleSubmit} className="pure-form pure-form-aligned">
          <LabeledInput onChange={this.handleInputChange} label="Login" name="username"/>
          <LabeledInput onChange={this.handleInputChange} label="Password" name="password" type="password"/>

          <div className="pure-controls">
            <button type="submit" className="pure-button pure-button-primary">Register</button>
          </div>
        </form>
      </div>
    );
  }

  handleSubmit = (e) => {
    e.preventDefault();
    const { username, password } = this.state;
    const { register } = this.props;
    register(username, password);
  }
}
