import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Link} from "react-router";
import {renderText} from "../constants/constants";
import {ADDRESS} from "../constants/contact";

export class ContactPage extends Component {

  componentDidMount() {
    const latitude = 50.0645191000000000;
    const longitude = 19.923639699999967;
    const description = 'aleja Adama Mickiewicza 30, Kraków, Polska';
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
    return (
      <div className="main">
        <h1>Contact</h1>
        <div className="container">
          <div className="details">
            <div className="paragraph">
              <h3>E-mail</h3>
              <div>To contact us, please e-mail <a
                  href="mailto:timebank.manager@gmail.com">timebank.manager@gmail.com
                </a>
              </div>
            </div>
            <div className="paragraph">
              <h3>Address</h3>
              <div className="address-view">
                <div className="column">
                  <p>You can also write to:</p>
                  <p className="bold">
                    {renderText(ADDRESS)}
                  </p>
                </div>
                <div className="column">
                  <div id="map"/>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

export default connect(

)(ContactPage);
