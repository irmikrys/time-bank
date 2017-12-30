import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Link} from "react-router";

export class AdvertView extends Component {

  render() {

    console.log(this.props);

    return (
      <div className="main">

        <h1>Advert</h1>
        <div className="container">
          <div className="details">
            <div className="paragraph">
              <h3>Advert id: {this.props.params.idAdvert}</h3>
              <div>Category: </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

export default connect()(AdvertView);
