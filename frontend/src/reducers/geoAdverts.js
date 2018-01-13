const FETCH_GEOADVERTS = 'adverts/FETCH_GEOADVERTS';
const FETCH_GEOADVERTS_SUCCESS = 'adverts/FETCH_GEOADVERTS_SUCCESS';
const FETCH_GEOADVERTS_FAIL = 'adverts/FETCH_GEOADVERTS_FAIL';

const initialState = {
  adverts: null
};

// Reducer

export default function advertsReducer(state = initialState, action) {
  switch (action.type) {
    case FETCH_GEOADVERTS:
      return {
        ...state,
        updating: true,
        adverts: null
      };
    case FETCH_GEOADVERTS_SUCCESS:
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

export function fetchGeoAdverts(locationCriteria) {
  const request = buildRequest(locationCriteria);
  return  {
    types: [FETCH_GEOADVERTS, FETCH_GEOADVERTS_SUCCESS, FETCH_GEOADVERTS_FAIL],
    promise: client => client
      .get(`/api/advertsNearMe?${request}`)
  };
}

export const buildRequest = locationCriteria => {
  return Object.keys(locationCriteria).reduce((previous, current) => `${previous}${current}=${locationCriteria[current]}&`, "");
};
