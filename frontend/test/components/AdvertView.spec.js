import { shallow } from 'enzyme';
import expect, { createSpy } from 'expect';
import React from 'react';
import AdvertView from '../../src/ui/component/adverts/AdvertView';

const advert = {
  advert: {
    idAdvert: 2,
    type: "SEEK",
    owner: "billgates",
    title: "Cat feeding",
    description: "I need somebody who feeds my cat when I am not present next week.",
    idCategory: 1,
    value: 3,
    createDate: 1515415299000,
    contractor: null
  },
  location: {
    description: "Osiedle Piastów, Kraków, Polska",
    latitude: 50.0996061,
    longitude: 20.016707399999973
  },
  userEmail: "billgates@gmail.com",
  interestedList: []
};
const categories = [{idCategory: 1, name: 'Cooking'}, {idCategory: 2, name: 'Tutoring'}, {idCategory: 3, name: 'Pet Care'}];
let props = {advert, categories};

describe('components', () => {

  describe('AdvertView', () => {

    it('should render two buttons to show interest and send message', () => {
      const component = shallow(<AdvertView {...props} username="stevejobs"/>);
      expect(component.find('button').length).toEqual(2);
      expect(component.find('button').first().childAt(0).hasClass('glyphicon-envelope'));
      expect(component.find('button').last().childAt(0).hasClass('glyphicon-star'));
    });

    it('should render two buttons to choose contractor and finalize offer', () => {
      const component = shallow(<AdvertView {...props} username="billgates"/>);
      expect(component.find('button').length).toEqual(2);
      expect(component.find('button').first().childAt(0).hasClass('glyphicon-erease'));
      expect(component.find('button').last().childAt(0).hasClass('glyphicon-brefcase'));
    });

  });
});
