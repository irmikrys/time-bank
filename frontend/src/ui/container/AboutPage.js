import React, {Component} from 'react';
import {connect} from 'react-redux';
import {ABOUT, HOW_IT_WORKS} from "../constants/aboutus";
import {Link} from "react-router";
import {renderText} from "../constants/constants";

export class AboutPage extends Component {

  render() {
    return (
      <div className="main">
        <h1>About Service</h1>
        <div className="container">
          <div className="details">
            <div className="paragraph">
              <h3>Welcome to Time Bank!</h3>
              <div>{renderText(ABOUT)}</div>
            </div>
            <div className="paragraph">
              <h3>How it works?</h3>
              <div>{renderText(HOW_IT_WORKS)}</div>
              <div className="more-info">
                <span>For more information about time banking visit </span>
                <a href="http://www.timebanking.org.uk/how_time_banking_works.asp"
                   target="_blank">
                  this page
                </a>
                <span>.</span>
              </div>
            </div>
            <div className="paragraph">
              <h3>How to start?</h3>
              <div>
                <span>It's a child's play! Just </span>
                <Link to="/register">register now</Link>
                <span> and start to share your time with other members.</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

export default connect()(AboutPage);
