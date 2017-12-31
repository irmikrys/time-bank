const FETCH_ADVERT = 'advert/FETCH_ADVERT';
const FETCH_ADVERT_SUCCESS = 'advert/FETCH_ADVERT_SUCCESS';
const FETCH_ADVERT_FAIL = 'advert/FETCH_ADVERT_FAIL';

const initialState = {
  updating: true,
  advert: {}
};

// Reducer

export default function advertReducer(state = initialState, action) {
  switch (action.type) {
    case FETCH_ADVERT:
      return {
        ...state,
        updating: true,
        advert: {}
      };
    case FETCH_ADVERT_SUCCESS:
      return {
        ...state,
        updating: false,
        advert: action.result.data
      };
    default:
      return state;
  }
}

// Actions

export function fetchAdvertById(id) {
  return  {
    types: [FETCH_ADVERT, FETCH_ADVERT_SUCCESS, FETCH_ADVERT_FAIL],
    promise: client => client.get(`/api/advert/${id}`)
  };
}
