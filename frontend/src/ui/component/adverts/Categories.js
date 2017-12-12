import React, {Component} from "react";
import CategoriesGrid from "./CategoriesGrid";

export default class Categories extends Component {

  componentDidMount() {
    this.props.fetchCategories()
  }

  render() {
    return (
      <div>
        <CategoriesGrid
          categories={this.props.categories}
        />
      </div>
    )
  }

}
