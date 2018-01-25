import React, {Component} from "react";
import Select from "react-select";
import Geosuggest from "react-geosuggest";
import { browserHistory } from 'react-router';
import * as _ from "underscore";
import {ADVERT_TYPE_OFFER} from "../../constants/constants";

export default class GeoSearch extends Component {

  constructor(props) {
    super(props);

    this.initalCriteria = {
      r: 0,
      lat: props.userLocation.latitude,
      lng: props.userLocation.longitude,
    };

    this.state = {
      locationCriteria: this.initalCriteria,
      map: null,
      markers: []
    }
  }

  componentDidMount() {
    this.initializeMap();
  }

  initializeMap() {
    const {lat, lng} = this.state.locationCriteria;
    let map = new window.google.maps.Map(document.getElementById('map'), {
      center: {
        lat: lat,
        lng: lng
      },
      zoom: 12,
      mapTypeId: 'roadmap',
    });
    new window.google.maps.Marker({
      map: map,
      position: {
        lat: lat,
        lng: lng
      },
      zIndex: 1,
      icon: require("my-location.png")
    });
    this.setState({map});
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.adverts) {
      this.clearMarkers();
      const markers = [];
      nextProps.adverts.map(advert => {
        const marker = new window.google.maps.Marker({
          map: this.state.map,
          position: {
            lat: advert.location.latitude,
            lng: advert.location.longitude
          },
          zIndex: 1,
          icon: advert.advert.type === ADVERT_TYPE_OFFER ? require("advert-offer-location.png") : require("advert-seek-location.png")
        });
        const content =
          `<h3>${advert.advert.title}</h3>` +
          `<p>${advert.advert.description}</p>`+
          `<p class="map-popup-paragraph" id=advert${advert.advert.idAdvert}>Click for more details</p>`;
        const infowindow = new window.google.maps.InfoWindow({content: content});
        markers.push(marker);
        marker.addListener('click', () => {
          infowindow.open(map, marker);
          document
            .getElementById(`advert${advert.advert.idAdvert}`)
            .addEventListener("click", () => {
              browserHistory.push(`/advert/${advert.advert.idAdvert}`);
            });
        });
      });
      this.setState({markers});
    }
  }

  clearMarkers() {
    this.state.markers.forEach(marker => marker.setMap(null));
  }

  handleLocationChange = value => {
    const locationCriteria = value ?
      {
        r: this.state.locationCriteria.r,
        lat: value.location.lat,
        lng: value.location.lng
      } : this.initalCriteria;
    this.setState({locationCriteria});
    this.initializeMap();
    this.props.fetchGeoAdverts(locationCriteria);
  };

  handleDistanceChange = value => {
    const locationCriteria = {
      r: value,
      lat: this.state.locationCriteria.lat,
      lng: this.state.locationCriteria.lng
    };
    this.setState({locationCriteria});
    this.props.fetchGeoAdverts(locationCriteria);
  };

  render() {
    return (
      <div className="geosearch-container">
        <div className="search-select-group">
          <div className="col-xs-6 select-group-item">
            <Geosuggest placeholder="choose location"
                        onSuggestSelect={this.handleLocationChange.bind(this)}
            />
          </div>
          <div className="col-xs-6 select-group-item">
            <Select simpleValue
                    placeholder="distance"
                    clearable={false}
                    value={this.state.locationCriteria.r}
                    onChange={this.handleDistanceChange.bind(this)}
                    options={_.range(5, 51, 5).map(item => { return {value: item, label: item} })}
            />
          </div>
        </div>
        {
          this.props.updatingAdverts ? <div className="loader position-fixed"/> : null
        }
        <div id="map" className={this.props.updatingAdverts ? "opacity" : ""}/>
      </div>
    )
  }

}
