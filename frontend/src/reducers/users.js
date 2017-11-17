const FETCH = 'users/FETCH';
const FETCH_SUCCESS = 'users/FETCH_SUCCESS';
const FETCH_FAIL = 'users/FETCH_FAIL';

const initialState = {
  items: []
};

// Reducer

export default function usersReducer(state = initialState, action) {
  switch (action.type) {
    case FETCH_SUCCESS:
      return {
        ...state,
        items: action.result.data
      };
    default:
      return state;
  }
}

// Actions

export function fetchUsers() {
  return  {
    types: [FETCH, FETCH_SUCCESS, FETCH_FAIL],
    promise: client => client.get('/api/users')
  };
}
