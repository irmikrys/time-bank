import React, { Component } from "react";
import {ErrorPanel} from "./ErrorPanel";
import {Link} from "react-router";

export default class AdvertForm extends Component {

  state = {
    title: "",
    description: ""
  };

  handleInputChange = event => {
    let value = event.target.value;
    let inputName = event.target.name;
    this.setState({[inputName]: value});
  };

  render() {
    return (
      <div className="advert-form">
        <div className="form-container">
          <form onSubmit={this.handleSubmit}>
            <input placeholder="title"
                   name="title"
                   onChange={this.handleInputChange}
                   required
            />
            <input placeholder="description"
                   name="description"
                   onChange={this.handleInputChange}
                   required
            />
            <button type="submit">Create Advert</button>
          </form>
        </div>
      </div>
    );
  }

  handleSubmit = event => {
    event.preventDefault();
    const { title, description } = this.state;
    const { createAdvert } = this.props;
    createAdvert(title, description);
  }
}
