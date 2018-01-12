const FETCH_CREATED_ADVERTS = 'adverts/FETCH_CREATED_ADVERTS';
const FETCH_CREATED_ADVERTS_SUCCESS = 'adverts/FETCH_CREATED_ADVERTS_SUCCESS';
const FETCH_CREATED_ADVERTS_FAIL = 'adverts/FETCH_CREATED_ADVERTS_FAIL';

const initialState = {
  updating: true,
  adverts: {}
};

// Reducer

export default function createdAdvertsReducer(state = initialState, action) {
  switch (action.type) {
    case FETCH_CREATED_ADVERTS:
      return {
        ...state,
        updating: true
      };
    case FETCH_CREATED_ADVERTS_SUCCESS:
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

export function fetchCreatedAdverts() {
  return  {
    types: [FETCH_CREATED_ADVERTS, FETCH_CREATED_ADVERTS_SUCCESS, FETCH_CREATED_ADVERTS_FAIL],
    promise: client => client.get(`/api/createdAdverts`)
  };
}
