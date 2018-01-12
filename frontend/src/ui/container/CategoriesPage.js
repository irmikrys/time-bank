import {connect} from 'react-redux';
import CategoriesGrid from "../component/adverts/CategoriesGrid";
import {fetchAdverts, setSearchCriteria} from "../../reducers/adverts";

export default connect(
  state => ({
    categories: state.categories.categories
  }),
  {setSearchCriteria, fetchAdverts}
)(CategoriesGrid);
