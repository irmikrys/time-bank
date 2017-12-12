import React, {Component} from "react";
import {addColor} from "../../constants/constants";
import {Link} from "react-router";

export default class CategoriesGrid extends Component {

  render() {
    return (
      <div className="categories-grid">
        <h1>Categories</h1>
        <div className="wrapper">
          {
            this.props.categories.map(addColor).map((item, i) => (
              <Link
                to={'/'}
                activeStyle={{ color: `#fff` }}
                activeClassName="wrapper"
              >
                <div key={item.category.idCategory}
                     className="tile"
                     style={{ height: `200px`, background: item.color }}
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
