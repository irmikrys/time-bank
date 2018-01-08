import React, { Component } from 'react';
import Geosuggest from "react-geosuggest";
import Select from "react-select";
import {ADVERT_TYPE_OFFER, ADVERT_TYPE_SEEK} from "../../constants/constants";

export default class EditAdvertModal extends Component {

  constructor(props) {
    super(props);
    const {advert, location} = props.advert;
    this.state = {
      type: advert.type,
      title: advert.title,
      description: advert.description,
      idCategory: advert.idCategory,
      value: advert.value,
      location: location
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

  handleCategoryChange = value => {
    this.setState({idCategory: value})
  };

  handleSubmit = event => {
    event.preventDefault();
    const { editAdvert, advert, closeModal, fetchAdvert } = this.props;
    editAdvert(advert.advert.idAdvert, this.state, closeModal, fetchAdvert);
  };

  render() {
    const offerAdvertTypeActive = this.state.type === ADVERT_TYPE_OFFER;
    return (
      <div>
        <div className="container edit-modal edit-advert-modal">
          <div className="details">
            <div className="paragraph">
              <div className="paragraph-title advert-form">
                <h3>Edit Advert</h3>
                <span onClick={this.props.closeModal} className="remove-icon glyphicon glyphicon-remove"/>
                <div>
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
                             value={ADVERT_TYPE_SEEK}
                             onChange={this.handleInputChange}
                             checked={!offerAdvertTypeActive}
                      /> SEEK
                    </label>
                  </div>
                  <Select simpleValue
                          placeholder="category"
                          clearable={false}
                          value={this.state.idCategory}
                          onChange={this.handleCategoryChange}
                          options={this.props.categories.map(item => { return {value: item.idCategory, label: item.name }})}
                  />
                  <input placeholder="hours"
                         name="value"
                         value={this.state.value}
                         onChange={this.handleInputChange}
                         required
                  />
                  <input placeholder="title"
                         name="title"
                         value={this.state.title}
                         onChange={this.handleInputChange}
                         required
                  />
                  <textarea placeholder="description..."
                            name="description"
                            value={this.state.description}
                            onChange={this.handleInputChange}
                            cols="40"
                            rows="5"
                            required
                  />
                  <Geosuggest placeholder="location"
                              initialValue={this.state.location.description}
                              onSuggestSelect={this.handleLocationSelect}
                  />
                  <button type="button" onClick={this.handleSubmit.bind(this)}>Save</button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div id="cover"/>
      </div>
    )};

}
