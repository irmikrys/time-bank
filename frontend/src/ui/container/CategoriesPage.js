import {connect} from 'react-redux';
import {fetchCategories} from '../../reducers/categories';
import CategoriesGrid from "../component/adverts/CategoriesGrid";
import {fetchAdverts, setSearchCriteria} from "../../reducers/adverts";

export default connect(
  state => ({
    categories: state.categories.categories
  }),
  {fetchCategories, setSearchCriteria, fetchAdverts}
)(CategoriesGrid);
