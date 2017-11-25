import React, { Component } from "react";
import Geosuggest from "react-geosuggest";

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

  handleLocationSelect = value => {
    console.log(value);
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
            <textarea placeholder="description..."
                      name="description"
                      onChange={this.handleInputChange}
                      cols="40"
                      rows="5"
                      required
            />
            <Geosuggest placeholder="location"
                        onSuggestSelect={this.handleLocationSelect}
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
