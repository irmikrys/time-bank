import React, {Component} from 'react';
import {connect} from 'react-redux';

export class HowItWorksPage extends Component {

  render() {
    return (
      <div className="main">
        <h1>How does it work?</h1>
      </div>
    )
  }
}

export default connect(

)(HowItWorksPage);
