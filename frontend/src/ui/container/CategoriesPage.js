import {connect} from 'react-redux';
import {fetchCategories} from '../../reducers/categories';
import Categories from "../component/adverts/Categories";

export default connect(
  state => ({
    categories: state.categories.categories
  }),
  {fetchCategories}
)(Categories);
