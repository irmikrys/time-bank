import React, {Component} from "react";
import Select from "react-select";
import {SORT_BY_SELECT_OPTIONS, TYPE_SELECT_OPTIONS} from "../../constants/constants";

export default class SearchBar extends Component {

  handleSelectChange = (field, value) => {
    let currentSortCriteria = Object.assign({}, this.props.searchCriteria, {[field]: value ? value : ""});
    this.props.onChange(currentSortCriteria);
  };

  handleInputChange = event => {
    let value = event.target.value;
    let inputName = event.target.name;
    this.setState({[inputName]: value});
  };

  handleClick = inputName => {
    let currentSortCriteria = Object.assign({}, this.props.searchCriteria, {[inputName]: this.state[inputName]});
    this.props.onChange(currentSortCriteria);
  };

  render() {
    return (
      <div className="search-bar-container container">
        <div className="search-bar">
          <div className="search-bar-item">
            <Select simpleValue
                    placeholder="type"
                    clearable={false}
                    value={this.props.searchCriteria.type}
                    options={TYPE_SELECT_OPTIONS}
                    onChange={this.handleSelectChange.bind(this, "type")}
            />
          </div>
          <div className="search-bar-item">
            <Select simpleValue
                    placeholder="category"
                    clearable={false}
                    value={this.props.searchCriteria.idCategory}
                    options={this.props.categories.map(item => { return {value: item.idCategory, label: item.name }})}
                    onChange={this.handleSelectChange.bind(this, "idCategory")}
            />
          </div>
          <div className="search-bar-item">
            <input placeholder="search for adverts..."
                   autoComplete="off"
                   name="phrase"
                   onChange={this.handleInputChange.bind(this)}
            />
            <span onClick={this.handleClick.bind(this, "phrase")} className="glyphicon glyphicon-search"/>
          </div>
          <div className="search-bar-item">
            <Select simpleValue
                    placeholder="sort by"
                    clearable={false}
                    value={this.props.searchCriteria.sort}
                    options={SORT_BY_SELECT_OPTIONS}
                    onChange={this.handleSelectChange.bind(this, "sort")}
            />
          </div>
        </div>
      </div>
    )
  }

}
