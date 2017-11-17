import RegisterForm from '../component/RegisterForm';
import {connect} from 'react-redux';
import {register} from '../../reducers/register';

export default connect(
  state => ({errorMessage: state.errorMessage}),
  {register}
)(RegisterForm);
