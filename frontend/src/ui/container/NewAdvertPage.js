import AdvertForm from '../component/forms/AdvertForm';
import {connect} from 'react-redux';
import {createAdvert} from '../../reducers/advertCreation';

export default connect(
  state => ({
    categories: state.categories.categories,
    username: state.authentication.username
  }),
  {createAdvert}
)(AdvertForm);
