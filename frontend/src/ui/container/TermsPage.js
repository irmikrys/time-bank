import React, {Component} from 'react';
import {connect} from 'react-redux';
import {
  changes, contact,
  content, links,
  purchases, termination,
  terms
} from "../constants/terms";

export class TermsPage extends Component {

  renderText() {

  }

  render() {
    return (
      <div>
        <h2>Terms of Service</h2>
        {terms.split("\n").map(i => {
          return <div>{i}</div>;
        })}
        {purchases.split("\n").map(i => {
          return <div>{i}</div>;
        })}
        {termination.split("\n").map(i => {
          return <div>{i}</div>;
        })}
        {content.split("\n").map(i => {
          return <div>{i}</div>;
        })}
        {links.split("\n").map(i => {
          return <div>{i}</div>;
        })}
        {changes.split("\n").map(i => {
          return <div>{i}</div>;
        })}
        {contact.split("\n").map(i => {
          return <div>{i}</div>;
        })}
      </div>
    )
  }
}

export default connect(

)(TermsPage);
