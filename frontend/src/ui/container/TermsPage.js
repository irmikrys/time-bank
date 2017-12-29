import React, {Component} from 'react';
import {connect} from 'react-redux';
import {TERMS} from "../constants/terms";
import {Link} from "react-router"
import {renderText} from "../constants/constants";

export class TermsPage extends Component {

  render() {
    return (
      <div className="main">
        <h1>Terms of Use</h1>
        <div className="container">
          <div className="details">
            <label>Last Updated: {new Date().toISOString().slice(0,10)}</label>
            {
              TERMS.map(item =>
                (
                  <div className="paragraph">
                    <h3>{item.header}</h3>
                    <div>{renderText(item.content)}</div>
                  </div>
                )
              )
            }
            <div className="paragraph">
              <h3>Contact</h3>
              <div>
                <span>If you have any questions, please </span>
                <Link to="/contact">contact us</Link>
                <span>.</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

export default connect()(TermsPage);
