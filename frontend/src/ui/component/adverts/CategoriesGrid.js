import React, {Component} from "react";
import {addColor} from "../../constants/constants";
import {Link} from "react-router";

export default class CategoriesGrid extends Component {

  setSearchCriteriaAndFetch = idCategory => {
    this.props.setSearchCriteria({idCategory});
    this.props.fetchAdverts({idCategory});
  };

  render() {
    return (
      <div className="categories-grid">
        <h1>Categories</h1>
        <div className="wrapper">
          {
            this.props.categories.map(addColor).map((item, i) => (
              <Link to={'/'} key={item.category.idCategory} onClick={this.setSearchCriteriaAndFetch.bind(this, item.category.idCategory)}>
                <div className="tile"
                     style={{ background: item.color }}
                >
                  <p>
                    {item.category.name}
                  </p>
                </div>
              </Link>
            ))
          }
        </div>
      </div>

    );
  }
}
