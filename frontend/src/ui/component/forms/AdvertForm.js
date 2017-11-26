import React, { Component } from "react";
import Select from "react-select";
import Geosuggest from "react-geosuggest";
import {ADVERT_TYPE_OFFER, ADVERT_TYPE_REQUEST} from "../../constants/constants";

export default class AdvertForm extends Component {

  state = {
    username: this.props.username,
    type: ADVERT_TYPE_OFFER,
    title: "",
    description: "",
    idCategory: "",
    value: "2",
    location: {}
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

  handleCategoryChange = value => {
    this.setState({idCategory: value})
  };

  render() {
    const offerAdvertTypeActive = this.state.type === ADVERT_TYPE_OFFER;
    return (
      <div className="advert-form">
        <div className="form-container">
          <form onSubmit={this.handleSubmit}>
            <div className="toggle" data-toggle="buttons">
              <label className={`btn btn-primary toggle-btn ${offerAdvertTypeActive ? "active" : ""}`}>
                <input type="radio"
                       name="type"
                       value={ADVERT_TYPE_OFFER}
                       onChange={this.handleInputChange}
                       checked={offerAdvertTypeActive}
                /> OFFER
              </label>
              <label className={`btn btn-primary toggle-btn ${!offerAdvertTypeActive ? "active" : ""}`}>
                <input type="radio"
                       name="type"
                       value={ADVERT_TYPE_REQUEST}
                       onChange={this.handleInputChange}
                       checked={!offerAdvertTypeActive}
                /> REQUEST
              </label>
            </div>
            <Select simpleValue
                    placeholder="category"
                    clearable={false}
                    value={this.state.idCategory}
                    onChange={this.handleCategoryChange}
                    options={this.props.categories.map(item => { return {value: item.idCategory, label: item.name }})}
            />
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
    const { createAdvert } = this.props;
    createAdvert(this.state);
  }
}
