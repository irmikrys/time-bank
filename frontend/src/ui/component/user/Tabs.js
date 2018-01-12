import React, {Component} from 'react';

export const Tabs = props => (
  <ul className="nav nav-tabs">
    {
      props.tabData.map(tab => <Tab data={tab} isActive={props.activeTab === tab.name}
                              handleClick={props.changeTab.bind(this, tab.name)}/>)
    }
  </ul>
);

const Tab = props => (
  <li onClick={props.handleClick} className={props.isActive ? "active" : ""}>
    {props.data.name}
  </li>
);
