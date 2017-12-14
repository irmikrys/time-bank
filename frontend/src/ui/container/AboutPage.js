import React, {Component} from 'react';
import {connect} from 'react-redux';
import {about} from "../constants/aboutus";
import {Link} from "react-router";

export class AboutPage extends Component {

  render() {
    return (
      <div className="main">
        <h1>About us</h1>
        <div className="container">
          <div className="details">
            <h3>Welcome to Time Bank!</h3>
            {renderText(about)}
            <h3>How to start?</h3>
            <div>
              <span>It's a child's play! Just </span>
              <Link to="/register">register now</Link>
              <span> and start to share your time with other members.</span>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

export default connect(

)(AboutPage);


function renderText(text) {
  return(
    text.split("\n").map(i => {
      return <div>{i}</div>;
    }))
}
