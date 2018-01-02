import React, {Component} from 'react';
import {dateFormatter} from "../utils";


export default class AdvertView extends Component {

  componentDidMount() {
    const {latitude, longitude, description} = this.props.advert.location;
    let map = new window.google.maps.Map(document.getElementById('map'), {
      center: {
        lat: latitude,
        lng: longitude
      },
      zoom: 12,
      mapTypeId: 'roadmap',
    });
    new window.google.maps.Marker({
      map: map,
      position: {
        lat: latitude,
        lng: longitude
      },
      title: description,
      zIndex: 1
    });
  }

  render() {
    const {categories, username} = this.props;
    const {advert, location, interestedList} =  this.props.advert;
    const isUserInterested = interestedList.filter(item => item.username === username).length === 1;
    const star = isUserInterested ? "glyphicon-star" : "glyphicon-star-empty";
    return (
      <div className="container">
        <div className="details">
          <div className="paragraph">
            <p className="advert-title">
              <h3>{advert.title}</h3>
              <span className={`interested-star glyphicon ${star}`}/>
            </p>
            <div className="advert-view">
              <div className="column">
                <div className="three-column">
                  <label>Type:</label>
                  <div>{advert.type}</div>
                </div>
                <div className="three-column">
                  <label>Hours:</label>
                  <div>{advert.value}</div>
                </div>
                <div className="three-column">
                  <label>Category:</label>
                  <div>{categories.filter(e => e.idCategory === advert.idCategory)[0].name}</div>
                </div>
                <label className="margin-top-2">Description:</label>
                <div>{advert.description}</div>
                <label className="margin-top-2">Location:</label>
                <div>{location.description}</div>
                <label className="margin-top-2">Created By:</label>
                <div>{advert.employer}</div>
                <label className="margin-top-2">Create Date:</label>
                <div>{dateFormatter(new Date(advert.createDate))}</div>
                <label className="margin-top-2">Interested:</label>
                <div>{interestedList.length}</div>
              </div>
              <div className="column">
                <div id="map"/>
                <div className="advert-buttons">
                  <button type="button" >
                    <span className="glyphicon glyphicon-envelope"/> Ask about details
                  </button>
                  <button type="button" >
                    <span className="glyphicon glyphicon-star"/> I'm interested
                  </button>
              </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}
