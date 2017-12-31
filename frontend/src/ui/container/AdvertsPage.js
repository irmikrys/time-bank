import {connect} from 'react-redux';
import {fetchAdverts, setSearchCriteria} from '../../reducers/adverts';
import Adverts from "../component/adverts/Adverts";

export default connect(
  state => ({
    adverts: state.adverts.adverts,
    updating: state.adverts.updating,
    categories: state.categories.categories,
    searchCriteria: state.adverts.searchCriteria
  }),
  {fetchAdverts, setSearchCriteria}
)(Adverts);
