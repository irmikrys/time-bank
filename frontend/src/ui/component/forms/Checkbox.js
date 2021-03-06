import React, { Component } from 'react';

class Checkbox extends Component {

  state = {
    isChecked: false,
  };

  toggleCheckboxChange = () => {
    const { handleCheckboxChange, label } = this.props;

    this.setState(({ isChecked }) => ({
        isChecked: !isChecked
      }
    ));

    handleCheckboxChange(label);
  };

  render() {
    const { label } = this.props;
    const { isChecked } = this.state;

    return (
      <div className="checkbox-register">
        <input
          type="checkbox"
          value={label}
          checked={isChecked}
          onChange={this.toggleCheckboxChange}
        />
        <label className="checkbox-label">{label}</label>
      </div>
    );
  }
}

export default Checkbox;
