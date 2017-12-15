import React, {Component} from 'react';
import {connect} from 'react-redux';
import {TERMS} from "../constants/terms";

export class TermsPage extends Component {

  render() {
    return (
      <div className="terms-main">
        <h1>Terms of Service</h1>
        <div className="container">
          <div className="terms-details">
            <label>Last Updated: {new Date().toISOString().slice(0,10)}</label>
            {
              TERMS.map(item =>
                (
                  <div className="terms-paragraph">
                    <h3>{item.header}</h3>
                    <div>{renderText(item.content)}</div>
                  </div>
                )
              )
            }
          </div>
        </div>
      </div>
    )
  }
}

export default connect()(TermsPage);

const renderText = text => {
  return(
  text.split("\n").map(i => {
    return <p>{i}</p>;
  }))
};
