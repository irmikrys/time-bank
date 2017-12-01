const FETCH_ADVERTS = 'adverts/FETCH_ADVERTS';
const FETCH_ADVERTS_SUCCESS = 'adverts/FETCH_ADVERTS_SUCCESS';
const FETCH_ADVERTS_FAIL = 'adverts/FETCH_ADVERTS_FAIL';

const initialState = {
  updating: true,
  adverts: {}
};

// Reducer

export default function advertsReducer(state = initialState, action) {
  switch (action.type) {
    case FETCH_ADVERTS:
      return {
        ...state,
        updating: true,
      };
    case FETCH_ADVERTS_SUCCESS:
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

export function fetchAdverts() {
  return  {
    types: [FETCH_ADVERTS, FETCH_ADVERTS_SUCCESS, FETCH_ADVERTS_FAIL],
    promise: client => client.get("/api/adverts?page=0&size=10")
  };
}
