import React, { Component } from 'react';
import Geosuggest from "react-geosuggest";
import Select from "react-select";
import {ADVERT_TYPE_OFFER, ADVERT_TYPE_SEEK} from "../../constants/constants";

export default class SendEmailModal extends Component {

  constructor(props) {
    super(props);
    this.state = {
      email: props.userEmail,
      title: "",
      content: ""
    };
  }

  handleInputChange = event => {
    let value = event.target.value;
    let inputName = event.target.name;
    this.setState({[inputName]: value});
  };

  handleSubmit = event => {
    event.preventDefault();
    const { sendEmail, closeModal} = this.props;
    sendEmail(this.state);
    closeModal();
  };

  render() {
    return (
      <div>
        <div className="container send-email-modal">
          <div className="details">
            <div className="paragraph">
              <div className="paragraph-title advert-form">
                <h3>Send Email</h3>
                <span onClick={this.props.closeModal} className="remove-icon glyphicon glyphicon-remove"/>
                <div>
                  <input placeholder="title"
                         name="title"
                         value={this.state.value}
                         onChange={this.handleInputChange}
                         required
                  />
                  <textarea placeholder="What do you want to know?"
                            name="content"
                            onChange={this.handleInputChange}
                            cols="40"
                            rows="10"
                            required
                  />
                  <button type="button" onClick={this.handleSubmit.bind(this)}>
                    <span className="glyphicon glyphicon-send"/> Send
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div id="cover"/>
      </div>
    )};

}
