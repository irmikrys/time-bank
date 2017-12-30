import React, {Component} from "react";
import Select from "react-select";

export default class SearchBar extends Component {

  render() {
    return (
      <div className="search-bar-container container">
        <div className="search-bar">
          <div className="search-bar-item">
            <Select simpleValue
                    placeholder="type"
                    clearable={false}
                    value={null}
                    options={[{value: "SEEK", label: "SEEK"}, {value: "OFFER", label: "OFFER"}]}
            />
          </div>
          <div className="search-bar-item">
            <Select simpleValue
                    placeholder="category"
                    clearable={false}
                    value={"1"}
                    options={this.props.categories.map(item => { return {value: item.idCategory, label: item.name }})}
            />
          </div>
          <div className="search-bar-item">
            <input placeholder="search for adverts..."
                   name="search"
                   onChange={null}
            />
            <span className="glyphicon glyphicon-search"/>
          </div>
        </div>
      </div>
    )
  }

}
