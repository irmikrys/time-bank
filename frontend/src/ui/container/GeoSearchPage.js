import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchGeoAdverts} from '../../reducers/geoAdverts';
import GeoSearch from "../component/adverts/GeoSearch";
import {fetchUserByUsername} from "../../reducers/user";


class GeoSearchPage extends Component {

  constructor(props) {
    super(props);
    props.fetchUserByUsername(props.username);
  }

  render() {
    return (
      <div className="main">
        <h1>GeoSearch</h1>
        {
          this.props.updatingUser
            ?
            <div className="loader"/> :
            <GeoSearch userLocation={this.props.user.location}
                       adverts={this.props.adverts}
                       updatingAdverts={this.props.updatingAdverts}
                       fetchGeoAdverts={this.props.fetchGeoAdverts}

            />
        }
      </div>
    )
  }
}
export default connect(
  state => ({
    username: state.authentication.username,
    user: state.user.user,
    updatingUser: state.user.updating,
    adverts: state.geoAdverts.adverts,
    updatingAdverts: state.geoAdverts.updating,
  }),
  {fetchGeoAdverts, fetchUserByUsername}
)(GeoSearchPage);
