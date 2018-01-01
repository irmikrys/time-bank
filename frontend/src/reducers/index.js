import { combineReducers } from 'redux';
import users from './users';
import authentication from './authentication';
import register from './register';
import { routerReducer as routing } from 'react-router-redux';
import categories from "./categories";
import adverts from "./adverts";
import advert from "./advert";
import user from "./user";

export default combineReducers({
  users,
  authentication,
  register,
  categories,
  adverts,
  advert,
  user,
  routing
});
