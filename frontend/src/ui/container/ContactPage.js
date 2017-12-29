import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Link} from "react-router";
import {renderText} from "../constants/constants";
import {ADDRESS} from "../constants/contact";

export class ContactPage extends Component {

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
              <div>You can also write to: </div>
              <div className="bold">
                {renderText(ADDRESS)}
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
