import React, { Component } from "react";
import { Link } from "react-router";
import { connect } from "react-redux";
import {displayAuthError, getSession} from "../../reducers/authentication";
import "stylus/main.styl";
import {FOOTER, MENU_FOR_GUEST, MENU_FOR_USER} from "../constants/constants";
import {fetchCategories} from "../../reducers/categories";
import {setSearchCriteria} from "../../reducers/adverts";


const TopMenu = (props) => {
  const items = props.items.map((item, key) => (
    <li key={key}>
      <Link to={item.link} onClick={props.onClick}>{item.label}</Link>
    </li>
  ));
  return (
    <nav>
      <div className="container-fluid">
        <ul className="nav navbar-nav navbar-right">
          {items}
        </ul>
      </div>
    </nav>
  );
};

const FooterMenu = (props) => {
  const items = props.items.map((item, key) => (
    <li key={key}>
      <Link to={item.link}>{item.label}</Link>
    </li>
  ));
  return (
    <nav className="footer">
      <div className="container-fluid">
        <ul className="nav navbar-nav navbar-left">
          {items}
        </ul>
      </div>
    </nav>
  );
};

export class App extends Component {

  constructor(props) {
    super(props);
    this.props.fetchCategories();
  }

  componentDidMount() {
    this.props.getSession();
  }

  clearErrorMessagesAndSearchCriteria = () => {
    this.props.setSearchCriteria({});
    this.props.displayAuthError(null);
  };

  render() {
    const {isAuthenticated} = this.props;
    const menuItems = isAuthenticated ? MENU_FOR_USER : MENU_FOR_GUEST;

    return (
      <div id="application">
        <TopMenu onClick={this.clearErrorMessagesAndSearchCriteria.bind(this)} items={menuItems}/>
        {this.props.children}
        <FooterMenu items={FOOTER}/>
      </div>
    );
  }
}

export default connect(
  state => ({isAuthenticated: state.authentication.isAuthenticated}),
  {getSession, fetchCategories, setSearchCriteria, displayAuthError}
)(App);
