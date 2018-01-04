import React from 'react';
import { Route, Redirect, IndexRoute } from 'react-router';

import privateRoute from './privateRoute';
import App from '../ui/container/App';
import LoginPage from '../ui/container/LoginPage';
import RegisterPage from '../ui/container/RegisterPage';
import NewAdvertPage from "../ui/container/NewAdvertPage";
import AdvertsPage from "../ui/container/AdvertsPage";
import TermsPage from "../ui/container/TermsPage";
import CategoriesPage from "../ui/container/CategoriesPage";
import AboutPage from "../ui/container/AboutPage";
import ContactPage from "../ui/container/ContactPage";
import AdvertViewPage from "../ui/container/AdvertViewPage";
import UserPage from "../ui/container/UserPage";
import EditProfilePage from "../ui/container/EditProfilePage";

export default (onLogout) => (
  <Route path="/" name="app" component={App}>
    <IndexRoute component={AdvertsPage}/>
    <Route path="register" component={RegisterPage}/>
    <Route path="login" component={LoginPage}/>
    <Route path="logout" onEnter={onLogout}/>
    <Route path="new_advert" component={NewAdvertPage}/>
    <Route path="terms" component={TermsPage}/>
    <Route path="categories" component={CategoriesPage}/>
    <Route path="about" component={AboutPage}/>
    <Route path="contact" component={ContactPage}/>
    <Route path="advert/:idAdvert" component={privateRoute(AdvertViewPage)}/>
    <Route path="profile/:username" component={privateRoute(UserPage)}/>
    <Route path="edit_profile/:username" component={privateRoute(EditProfilePage)}/>
  </Route>
);
