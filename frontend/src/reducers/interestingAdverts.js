const FETCH_INTERESTING_ADVERTS = 'adverts/FETCH_INTERESTING_ADVERTS';
const FETCH_INTERESTING_ADVERTS_SUCCESS = 'adverts/FETCH_INTERESTING_ADVERTS_SUCCESS';
const FETCH_INTERESTING_ADVERTS_FAIL = 'adverts/FETCH_INTERESTING_ADVERTS_FAIL';

const initialState = {
  updating: true,
  adverts: {}
};

// Reducer

export default function interestingAdvertsReducer(state = initialState, action) {
  switch (action.type) {
    case FETCH_INTERESTING_ADVERTS:
      return {
        ...state,
        updating: true
      };
    case FETCH_INTERESTING_ADVERTS_SUCCESS:
      return {
        ...state,
        updating: false,
        adverts: action.result.data
      };
    default:
      return state;
  }
}

// Actions

export function fetchInterestingAdverts() {
  return  {
    types: [FETCH_INTERESTING_ADVERTS, FETCH_INTERESTING_ADVERTS_SUCCESS, FETCH_INTERESTING_ADVERTS_FAIL],
    promise: client => client.get(`/api/interestingAdverts`)
  };
}
