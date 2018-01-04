import EditProfileForm from '../component/forms/EditProfileForm';
import {connect} from 'react-redux';
import {editProfile} from '../../reducers/editProfile';

export default connect(
  state => ({
    username: state.user.username
  }),
  {editProfile}
)(EditProfileForm);
