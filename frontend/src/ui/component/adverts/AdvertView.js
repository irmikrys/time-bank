import React, {Component} from 'react';
import {dateFormatter} from "../utils";
import axios from 'axios';

export default class AdvertView extends Component {

  constructor(props) {
    super(props);

    const {interestedList, advert} = props.advert;
    this.state = {
      numberOfInterested: interestedList.length,
      isUserInterested: interestedList.filter(item => item.username === props.username).length === 1,
      isUserOwner: advert.employer === props.username
    };
  }

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

  handleOnClick = event => {
    const {idAdvert} =  this.props.advert.advert;
    axios.put(`/api/advert/switchInterest/${idAdvert}`, null)
      .then(result => {
        const {numberOfInterested, isUserInterested} = this.state;
        this.setState({
          numberOfInterested: isUserInterested ? numberOfInterested - 1 : numberOfInterested + 1,
          isUserInterested: !isUserInterested,
        });
      });
  };

  render() {
    const {categories, username} = this.props;
    const {advert, location, userEmail} =  this.props.advert;
    const {numberOfInterested, isUserInterested, isUserOwner} = this.state;
    const star = isUserInterested ? "glyphicon-star" : "glyphicon-star-empty";
    return (
      <div className="container">
        <div className="details">
          <div className="paragraph">
            <div className="advert-title">
              <h3>{advert.title}</h3>
              {!isUserOwner ? <span onClick={this.handleOnClick.bind(this)} className={`interested-star glyphicon ${star}`}/> : ""}
            </div>
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
                <div>{numberOfInterested}</div>
              </div>
              <div className="column">
                <div id="map"/>
                {
                  !isUserOwner ?
                    <div className="advert-buttons">
                      <a href={`mailto:${userEmail}`}>
                        <button type="button" >
                          <span className="glyphicon glyphicon-envelope"/> Ask about details
                        </button>
                      </a>
                      <button type="button" onClick={this.handleOnClick.bind(this)}>
                        <span className="glyphicon glyphicon-star"/> I'm {isUserInterested ? "not " : ""}interested
                      </button>
                    </div> : null
                }
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}
