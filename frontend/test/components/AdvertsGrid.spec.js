import { shallow } from 'enzyme';
import expect from 'expect';
import React from 'react';
import AdvertsGrid from '../../src/ui/component/adverts/AdvertsGrid';

const adverts = [
  {
    idAdvert: 2,
    type: "SEEK",
    owner: "billgates",
    title: "Cat feeding",
    description: "I need somebody who feeds my cat when I am not present next week.",
    idCategory: 1,
    value: 3,
    createDate: 1515415299000
  },
  {
    idAdvert: 3,
    type: "OFFER",
    owner: "stevejobs",
    title: "Help with Java",
    description: "I can teach somebody Java programming (OOP, concurrent etc.)",
    idCategory: 2,
    value: 5,
    createDate: 1515415336000
  }
];
const categories = [{idCategory: 1, name: 'Cooking'}, {idCategory: 2, name: 'Tutoring'}, {idCategory: 3, name: 'Pet Care'}];
let props = {adverts, categories};

describe('components', () => {

  describe('AdvertsGrid', () => {

    it('should render two Link items', () => {
      const component = shallow(<AdvertsGrid {...props} />);
      expect(component.find('Link').length).toEqual(2);
    });

    it('should contains card wrapped with Links components', () => {
      const component = shallow(<AdvertsGrid {...props} />);
      component.first('Link').forEach(node => {
        node.childAt(0).hasClass('card');
      });
    })
  });
});
