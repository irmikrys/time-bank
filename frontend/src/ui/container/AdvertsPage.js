import {connect} from 'react-redux';
import {fetchAdverts} from '../../reducers/adverts';
import Adverts from "../component/adverts/Adverts";

export default connect(
  state => ({
    adverts: state.adverts.adverts,
    updating: state.adverts.updating,
    categories: state.categories.categories
  }),
  {fetchAdverts}
)(Adverts);
