import React, {Component} from "react";
import AdvertsGrid from './AdvertsGrid';
import SearchBar from "./SearchBar";

export default class Adverts extends Component {

  componentDidMount() {
    this.props.fetchAdverts()
  }

  render() {
    return (
      <div>
        <SearchBar categories={this.props.categories} searchCriteria={this.props.searchCriteria} />
        {
          !this.props.updating ?
            <AdvertsGrid adverts={this.props.adverts.content}
                         categories={this.props.categories}
            />
            : <div className="loader"/>
        }
      </div>
    )
  }

}
