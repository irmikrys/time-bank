const FETCH_CATEGORIES = 'categories/FETCH_CATEGORIES';
const FETCH_CATEGORIES_SUCCESS = 'categories/FETCH_CATEGORIES_SUCCESS';
const FETCH_CATEGORIES_FAIL = 'categories/FETCH_CATEGORIES_FAIL';

const initialState = {
  categories: []
};

// Reducer

export default function categoriesReducer(state = initialState, action) {
  switch (action.type) {
    case FETCH_CATEGORIES_SUCCESS:
      return {
        ...state,
        categories: action.result.data
      };
    default:
      return state;
  }
}

// Actions

export function fetchCategories() {
  return  {
    types: [FETCH_CATEGORIES, FETCH_CATEGORIES_SUCCESS, FETCH_CATEGORIES_FAIL],
    promise: client => client.get('/api/categories')
  };
}
