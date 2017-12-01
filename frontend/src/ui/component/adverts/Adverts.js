import React, {Component} from "react";
import AdvertsGrid from './AdvertsGrid';

export default class Adverts extends Component {

  componentDidMount() {
    this.props.fetchAdverts()
  }

  render() {
    return (
      <div>
        {
          this.props.updating ?
            null :
            <AdvertsGrid adverts={this.props.adverts.content}
                         categories={this.props.categories}
            />
        }
      </div>
    )
  }

}
