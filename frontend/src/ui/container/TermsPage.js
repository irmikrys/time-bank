import React, {Component} from 'react';
import {connect} from 'react-redux';

export class TermsPage extends Component {

  render() {
    const text = "Last updated: 2017-11-30 \n" +
      "Please read these Terms of Use (\"Terms\", \"Terms of Use\") " +
      "carefully before using the http://www.mywebsite.com (change this) " +
      "website My Company (change this) (\"us\", \"we\", or \"our\"). " +
      "\nYour access to and use of the Service is conditioned on your " +
      "acceptance of and compliance with these Terms. " +
      "\nThese Terms apply to all visitors, users and others " +
      "who access or use the Service." +
      "\nBy accessing or using the Service you agree to be bound by these Terms. " +
      "\nIf you disagree with any part of the terms then you may not access the Service.";

    return (
      <div>
        <h2>Terms of Use ("Terms")</h2>
        {text.split("\n").map(i => {
          return <div>{i}</div>;
        })}
      </div>
    )
  }
}

export default connect(

)(TermsPage);
