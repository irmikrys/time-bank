import ListComponent from '../component/ListComponent';
import {connect} from 'react-redux';
import {fetchUsers} from '../../reducers/users';

export default connect(
  state => ({items: state.users.items}),
  {fetchUsers}
)(ListComponent);
