import LoginForm from '../component/forms/LoginForm';
import {connect} from 'react-redux';
import {login} from '../../reducers/authentication';

export default connect(
  state => ({
    errorMessage: state.authentication.errorMessage,
    registerSuccess: state.authentication.registerSuccess
  }),
  {login}
)(LoginForm);
