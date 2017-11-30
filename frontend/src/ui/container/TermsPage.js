import React, {Component} from 'react';
import {connect} from 'react-redux';
import {
  terms
} from "../constants/constants";

export class TermsPage extends Component {

  render() {
    return (
      <div>
        <h2>Terms of Use ("Terms")</h2>
        {terms.split("\n").map(i => {
          return <div>{i}</div>;
        })}
      </div>
    )
  }
}

export default connect(

)(TermsPage);
