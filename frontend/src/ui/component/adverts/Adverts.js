import React, {Component} from "react";
import AdvertsGrid from './AdvertsGrid';
import SearchBar from "./SearchBar";

export default class Adverts extends Component {

  componentDidMount() {
    this.props.fetchAdverts(this.props.searchCriteria)
  }

  handleSearchBarChange = searchCriteria => {
    this.props.setSearchCriteria(searchCriteria);
    this.props.fetchAdverts(searchCriteria);
  };

  render() {
    return (
      <div>
        <SearchBar categories={this.props.categories}
                   searchCriteria={this.props.searchCriteria}
                   onChange={this.handleSearchBarChange.bind(this)}
        />
        {
          !this.props.updating ?
            <AdvertsGrid adverts={this.props.adverts.content}
                         categories={this.props.categories}
                         searchCriteria={this.props.searchCriteria}
            />
            : <div className="loader"/>
        }
      </div>
    )
  }

}
