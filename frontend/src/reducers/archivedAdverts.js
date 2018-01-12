const FETCH_ARCHIVED_ADVERTS = 'adverts/FETCH_ARCHIVED_ADVERTS';
const FETCH_ARCHIVED_ADVERTS_SUCCESS = 'adverts/FETCH_ARCHIVED_ADVERTS_SUCCESS';
const FETCH_ARCHIVED_ADVERTS_FAIL = 'adverts/FETCH_ARCHIVED_ADVERTS_FAIL';

const initialState = {
  updating: true,
  adverts: {}
};

// Reducer

export default function archivedAdvertsReducer(state = initialState, action) {
  switch (action.type) {
    case FETCH_ARCHIVED_ADVERTS:
      return {
        ...state,
        updating: true
      };
    case FETCH_ARCHIVED_ADVERTS_SUCCESS:
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

export function fetchArchivedAdverts() {
  return  {
    types: [FETCH_ARCHIVED_ADVERTS, FETCH_ARCHIVED_ADVERTS_SUCCESS, FETCH_ARCHIVED_ADVERTS_FAIL],
    promise: client => client.get(`/api/archive`)
  };
}
