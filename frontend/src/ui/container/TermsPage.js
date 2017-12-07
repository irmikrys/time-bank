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
              {terms.split("\n").map(i => {
                return <div>{i}</div>;
              })}
            <h3>Purchases</h3>
              {purchases.split("\n").map(i => {
                return <div>{i}</div>;
              })}
            <h3>Termination</h3>
              {termination.split("\n").map(i => {
                return <div>{i}</div>;
              })}
            <h3>Content</h3>
              {content.split("\n").map(i => {
                return <div>{i}</div>;
              })}
            <h3>Links To Other Websites</h3>
              {links.split("\n").map(i => {
                return <div>{i}</div>;
              })}
            <h3>Changes</h3>
              {changes.split("\n").map(i => {
                return <div>{i}</div>;
              })}
            <h3>Contact Us</h3>
              {contact.split("\n").map(i => {
                return <div>{i}</div>;
              })}
          </div>
        </div>
      </div>
    )
  }
}

export default connect(

)(TermsPage);
