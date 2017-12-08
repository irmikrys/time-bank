import React, {Component} from 'react';
import {connect} from 'react-redux';
import {
  changes, contact,
  content, links,
  purchases, termination,
  terms
} from "../constants/terms";

export class TermsPage extends Component {

  render() {
    return (
      <div className="terms-main">
        <h1>Terms of Service</h1>
        <div className="container">
          <div className="terms-details">
            <label>Last Updated: {new Date().toISOString().slice(0,10)}</label>
            <h3>General</h3>
              {renderText(terms)}
            <h3>Purchases</h3>
              {renderText(purchases)}
            <h3>Termination</h3>
              {renderText(termination)}
            <h3>Content</h3>
              {renderText(content)}
            <h3>Links To Other Websites</h3>
              {renderText(links)}
            <h3>Changes</h3>
              {renderText(changes)}
            <h3>Contact Us</h3>
              {renderText(contact)}
          </div>
        </div>
      </div>
    )
  }
}

export default connect(

)(TermsPage);

function renderText(text) {
  return(
  text.split("\n").map(i => {
    return <div>{i}</div>;
  }))
}
