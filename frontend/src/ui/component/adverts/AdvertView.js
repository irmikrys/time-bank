import React, {Component} from 'react';
import {dateFormatter} from "../utils";

export default class AdvertView extends Component {

  render() {
    const {advert, categories} = this.props;
    return (
      <div className="container">
        <div className="details">
          <div className="paragraph">
            <h3>{advert.title}</h3>
            <div className="advert-details">
              <label>Type:</label>
              <div>{advert.type}</div>
              <label>Category:</label>
              <div>{categories.filter(e => e.idCategory === advert.idCategory)[0].name}</div>
              <label>Hours:</label>
              <div>{advert.value}</div>
              <label>Description:</label>
              <div>{advert.description}</div>
              <label>Created By:</label>
              <div>{advert.employer}</div>
              <label>Create Date:</label>
              <div>{dateFormatter(new Date(advert.createDate))}</div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}
