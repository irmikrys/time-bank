import AdvertForm from '../component/forms/AdvertForm';
import {connect} from 'react-redux';
import {createAdvert} from '../../reducers/advertCreation';

export default connect(
  state => ({

  }),
  {createAdvert}
)(AdvertForm);
