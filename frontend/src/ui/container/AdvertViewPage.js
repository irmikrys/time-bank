import React, {Component} from 'react';
import {connect} from 'react-redux';
import AdvertView from "../component/adverts/AdvertView";
import {fetchAdvertById} from "../../reducers/advert";

export class AdvertViewPage extends Component {

  constructor(props) {
    super(props);
    props.fetchAdvertById(props.params.idAdvert);
  }

  render() {

    return (
      <div className="main">
        <h1>Advert</h1>
        {
          !this.props.updating ?
            <AdvertView advert={this.props.advert} categories={this.props.categories}/>
            : <div className="loader"/>
        }
      </div>
    )
  }
}

export default connect(
  state => ({
    advert: state.advert.advert,
    updating: state.advert.updating,
    categories: state.categories.categories
  }),
  {fetchAdvertById}
)(AdvertViewPage);
