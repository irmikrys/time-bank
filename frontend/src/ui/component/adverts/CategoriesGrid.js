import React, {Component} from "react";
import {addColor} from "../../constants/constants";
import {Link} from "react-router";

export default class CategoriesGrid extends Component {

  constructor(props) {
    super(props);

    this.state = {
      elements: this.props.categories.map(addColor)
    };

    console.log(this.state.elements.length)
  }

  render() {
    return (
      <div className="categories-grid">
        <h1>Categories</h1>
        <div className="wrapper">
          {
            this.state.elements.map((item, i) => (
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
                    {this.props.categories
                      .filter(e => e.idCategory === item.category.idCategory)[0].name}
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
