import React from 'react';
import { Route, Redirect, IndexRoute } from 'react-router';

import App from '../ui/container/App';
import UserProfile from '../ui/container/UserProfile';
import LoginPage from '../ui/container/LoginPage';
import RegisterPage from '../ui/container/RegisterPage';
import NewAdvertPage from "../ui/container/NewAdvertPage";
import AdvertsPage from "../ui/container/AdvertsPage";
import TermsPage from "../ui/container/TermsPage";
import privateRoute from './privateRoute';
import CategoriesPage from "../ui/container/CategoriesPage";

export default (onLogout) => (
  <Route path="/" name="app" component={App}>
    <IndexRoute component={AdvertsPage}/>
    <Route path="profile" component={privateRoute(UserProfile)}/>
    <Route path="register" component={RegisterPage}/>
    <Route path="login" component={LoginPage}/>
    <Route path="logout" onEnter={onLogout}/>
    <Route path="new_advert" component={NewAdvertPage}/>
    <Route path="terms" component={TermsPage}/>
    <Route path="categories" component={CategoriesPage}/>
    <Route path="adverts/:category" component={AdvertsPage}/>
  </Route>
);
