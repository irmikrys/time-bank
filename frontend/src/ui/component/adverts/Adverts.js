import React, {Component} from "react";
import AdvertsGrid from './AdvertsGrid';
import SearchBar from "./SearchBar";
import * as _ from "lodash";

export default class Adverts extends Component {

  constructor(props) {
    super(props);
    if(_.isEmpty(props.searchCriteria))
      props.fetchAdverts();

    this.state = {
      searchCriteria: props.searchCriteria
    };
  }

  handleSearchBarChange = searchCriteria => {
    this.props.setSearchCriteria(searchCriteria);
    this.props.fetchAdverts(searchCriteria);
  };

  componentWillReceiveProps(props) {
    if (props.searchCriteria !== this.state.searchCriteria) {
      props.fetchAdverts(props.searchCriteria);
      this.setState({searchCriteria: props.searchCriteria});
    }
  }

  render() {
    return (
      <div>
        <SearchBar categories={this.props.categories}
                   searchCriteria={this.state.searchCriteria}
                   onChange={this.handleSearchBarChange.bind(this)}
        />
        {
          !this.props.updating && <AdvertsGrid adverts={this.props.adverts.content}
                         categories={this.props.categories}
                         searchCriteria={this.state.searchCriteria}
          />
        }

        {
          this.props.updating && <div className="loader"/>
        }
      </div>
    )
  }

}
