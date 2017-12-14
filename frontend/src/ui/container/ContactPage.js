import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Link} from "react-router";

export class ContactPage extends Component {

  render() {
    return (
      <div className="main">
        <h1>Contact</h1>
        <div className="container">
          <div className="details">
            <label>e-mail: <a href="mailto:timebank.manager@gmail.com">timebank.manager@gmail.com</a>
            </label>
          </div>
        </div>
      </div>
    )
  }
}

export default connect(

)(ContactPage);
