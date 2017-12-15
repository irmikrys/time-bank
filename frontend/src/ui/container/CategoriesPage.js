import {connect} from 'react-redux';
import {fetchCategories} from '../../reducers/categories';
import CategoriesGrid from "../component/adverts/CategoriesGrid";

export default connect(
  state => ({
    categories: state.categories.categories
  }),
  {fetchCategories}
)(CategoriesGrid);
