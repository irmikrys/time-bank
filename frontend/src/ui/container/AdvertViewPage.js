import React, {Component} from 'react';
import {connect} from 'react-redux';
import AdvertView from "../component/adverts/AdvertView";
import {fetchAdvertById} from "../../reducers/advert";
import {setSearchCriteria} from "../../reducers/adverts";
import {editAdvert} from "../../reducers/editAdvert";
import {sendEmail} from "../../reducers/sendEmail";

export class AdvertViewPage extends Component {

  constructor(props) {
    super(props);
    props.fetchAdvertById(props.params.idAdvert);
  }

  render() {

    return (
      <div className="main">
        <h1>Advert</h1>
        { !this.props.updating && <AdvertView advert={this.props.advert}
                                              location={this.props.location}
                                              categories={this.props.categories}
                                              username={this.props.username}
                                              setSearchCriteria={this.props.setSearchCriteria}
                                              fetchAdvert={this.props.fetchAdvertById}
                                              editAdvert={this.props.editAdvert}
                                              sendEmail={this.props.sendEmail}
        />
        }
        { this.props.updating && <div className="loader"/> }
      </div>
    )
  }
}

export default connect(
  state => ({
    username: state.authentication.username,
    advert: state.advert.advert,
    updating: state.advert.updating,
    categories: state.categories.categories
  }),
  {fetchAdvertById, setSearchCriteria, editAdvert, sendEmail}
)(AdvertViewPage);
