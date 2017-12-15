import React, {Component} from "react";
import Masonry from 'react-masonry-infinite';
import axios from 'axios';
import {addColorAndHeight} from "../../constants/constants";
import {dateFormatter} from "../utils";

export default class AdvertsGrid extends Component {

  constructor(props) {
    super(props);

    this.state = {
      hasMore: true,
      elements: this.props.adverts.map(addColorAndHeight)
    };
  }

  loadMore = page => {
    axios.get(`/api/adverts?page=${page}&size=6`)
      .then(response => {
        setTimeout(() => {
          this.setState({
            hasMore: !response.data.last,
            elements: this.state.elements.concat(response.data.content.map(addColorAndHeight)
            )
          });
        }, 1500)
      })
  };

  render() {
    return (
      <div className="advert-grid">
        <h1>Adverts</h1>
        <div className="container">
          <Masonry
            className="masonry"
            hasMore={this.state.hasMore}
            loader={
              <div className="sk-folding-cube">
                <div className="sk-cube1 sk-cube" />
                <div className="sk-cube2 sk-cube" />
                <div className="sk-cube4 sk-cube" />
                <div className="sk-cube3 sk-cube" />
              </div>
            }
            loadMore={this.loadMore}
          >
            {
              this.state.elements.map((item, i) => (
                <div key={item.advert.idAdvert} className="card" style={{ height: item.height }}>
                  <h2 style={{ background: item.color }}>{item.advert.title}</h2>
                  <div className="advert-details">
                    <label>Type:</label>
                    <div>{item.advert.type}</div>
                    <label>Category:</label>
                    <div>{this.props.categories.filter(e => e.idCategory === item.advert.idCategory)[0].name}</div>
                    <label>Description:</label>
                    <div>{item.advert.description}</div>
                    <label>Hours</label>
                    <div>{item.advert.value}</div>
                    <label>Create Date:</label>
                    <div>{dateFormatter(new Date(item.advert.creationDate))}</div>
                  </div>
                </div>
              ))
            }
          </Masonry>
        </div>
      </div>

    );
  }
}
