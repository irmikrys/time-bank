import React, {Component} from 'react';
import {dateFormatter} from "../utils";


export default class AdvertView extends Component {

  componentDidMount() {
    let map = new window.google.maps.Map(document.getElementById('map'), {
      center: {
        lat: -33.8688,
        lng: 151.2195
      },
      zoom: 15,
      mapTypeId: 'roadmap',
    });
    new window.google.maps.Marker({
      map: map,
      position: {
        lat: -33.8688,
        lng: 151.2195
      }
        ,
    });
  }

  render() {
    const {advert, categories} = this.props;
    return (
      <div className="container">
        <div className="details">
          <div className="paragraph">
            <h3>{advert.title}</h3>
            <div className="advert-view">
              <div className="column">
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
              <div className="column">
                <div id="map"/>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}
