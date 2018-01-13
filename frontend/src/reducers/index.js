import { combineReducers } from 'redux';
import users from './users';
import authentication from './authentication';
import register from './register';
import { routerReducer as routing } from 'react-router-redux';
import categories from "./categories";
import adverts from "./adverts";
import createdAdverts from "./createdAdverts";
import interestingAdverts from "./interestingAdverts";
import advert from "./advert";
import geoAdverts from "./geoAdverts";
import user from "./user";
import editProfile from "./editProfile";
import archivedAdverts from "./archivedAdverts";
import editAdvert from "./editAdvert";
import sendEmail from "./sendEmail";

export default combineReducers({
  users,
  authentication,
  register,
  categories,
  adverts,
  createdAdverts,
  interestingAdverts,
  archivedAdverts,
  geoAdverts,
  advert,
  user,
  editProfile,
  editAdvert,
  sendEmail,
  routing
});
