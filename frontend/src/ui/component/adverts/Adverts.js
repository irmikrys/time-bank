import React, {Component} from "react";
import AdvertsGrid from './AdvertsGrid';

export default class Adverts extends Component {

  componentDidMount() {
    this.props.fetchAdverts()
  }

  searchForCategory = categoryName => {
    return(
      this.props.categories.filter(e => e.name === categoryName)[0]
    )
  };

  advertsInCategory = categoryName => {
    const category = this.searchForCategory(categoryName);
    return(
      this.props.adverts.content.filter(e => e.idCategory === category.idCategory)
    )
  };

  render() {
    const isUndefined = this.props.params.category === undefined;
    const adverts = isUndefined ?
      this.props.adverts.content : this.advertsInCategory(this.props.params.category);
    return (
      <div>
        {
          this.props.updating ?
            null :
            <AdvertsGrid adverts={adverts}
                         categories={this.props.categories}
            />
        }
      </div>
    )
  }

}
