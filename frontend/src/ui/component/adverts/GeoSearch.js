import React, {Component} from "react";
import Select from "react-select";
import Geosuggest from "react-geosuggest";
import { browserHistory } from 'react-router';
import * as _ from "underscore";

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
      zIndex: 1
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
          zIndex: 1
        });
        markers.push(marker);
        google.maps.event.addListener(marker, 'click', this.redirectToAdvert.bind(this, advert.advert.idAdvert));
      });
      this.setState({markers});
    }
  }

  redirectToAdvert(idAdvert) {
    browserHistory.push(`/advert/${idAdvert}`);
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
      <div>
        <Geosuggest placeholder="choose location"
                    onSuggestSelect={this.handleLocationChange.bind(this)}
        />
        <Select simpleValue
                placeholder="distance"
                clearable={false}
                value={this.state.locationCriteria.r}
                onChange={this.handleDistanceChange.bind(this)}
                options={_.range(5, 51, 5).map(item => { return {value: item, label: item} })}
        />
        {
          this.props.updatingAdverts ? <div className="loader position-fixed"/> : null
        }
        <div id="map"/>
      </div>
    )
  }

}
