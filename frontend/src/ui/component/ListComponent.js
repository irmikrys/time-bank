import React, {Component} from 'react';

export default class ListComponent extends Component {
  render() {
    const items = this.props.items;
    const list = items.map((item, index) => <li key={index}>{item.username}</li>);

    return (
      <div>
        <h2>Users:</h2>
        <ul>
          {list}
        </ul>
        <button onClick={this.props.fetchUsers}>
          Fetch
        </button>
      </div>
    );
  }
}
