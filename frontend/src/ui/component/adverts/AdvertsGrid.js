import React, {Component} from "react";
import Masonry from 'react-masonry-infinite';
import axios from 'axios';
import {addColorAndHeight, PAGE_SIZE} from "../../constants/constants";
import {dateFormatter} from "../utils";
import {Link} from "react-router";
import {buildRequest} from "../../../reducers/adverts";

export default class AdvertsGrid extends Component {

  constructor(props) {
    super(props);

    this.state = {
      hasMore: this.props.adverts.length === PAGE_SIZE,
      elements: this.props.adverts.map(addColorAndHeight),
    };
  }

  loadMore = page => {
    axios.get(`/api/adverts?page=${page}&size=${PAGE_SIZE}${buildRequest(this.props.searchCriteria)}`)
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
        <div className="container">
          <Masonry
            className="masonry"
            elementType="a"
            hasMore={this.state.hasMore}
            loader={<div className="loader"/>}
            loadMore={this.loadMore.bind(this)}
            threshold={10}
          >
            {
              this.state.elements.map((item, i) => (
                <Link to={`/advert/${item.advert.idAdvert}`} key={item.advert.idAdvert}>
                  <div className="card" style={{ height: item.height }}>
                    <h2 style={{ background: item.color }}>{item.advert.title}</h2>
                    <div className="advert-details">
                      <label>Type:</label>
                      <div>{item.advert.type}</div>
                      <label>Category:</label>
                      <div>{this.props.categories.filter(e => e.idCategory === item.advert.idCategory)[0].name}</div>
                      <label>Description:</label>
                      <div>{item.advert.description}</div>
                      <label>Create Date:</label>
                      <div>{dateFormatter(new Date(item.advert.createDate))}</div>
                    </div>
                  </div>
                </Link>
              ))
            }
          </Masonry>
          { !this.state.hasMore && !this.state.elements.length ? <h3>No matching adverts</h3> : null }
        </div>
      </div>

    );
  }
}
