import { shallow } from 'enzyme';
import expect from 'expect';

import React from 'react';
import CategoriesGrid from '../../src/ui/component/adverts/CategoriesGrid';

const categories = [{idCategory: 1, name: 'Cooking'}, {idCategory: 2, name: 'Tutoring'}, {idCategory: 3, name: 'Pet Care'}];
let props = {categories};

describe('components', () => {

  describe('CategoriesGrid', () => {

    it('should render three Link items', () => {
      const component = shallow(<CategoriesGrid {...props} />);
      expect(component.find('Link').length).toEqual(3);
    });

    it('should contains tiles wrapped with Links components', () => {
      const component = shallow(<CategoriesGrid {...props} />);
      component.first('Link').forEach(node => {
        node.childAt(0).hasClass('tile');
      });
    })
  });
});
