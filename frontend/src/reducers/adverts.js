import {PAGE_SIZE} from "../ui/constants/constants";

const FETCH_ADVERTS = 'adverts/FETCH_ADVERTS';
const FETCH_ADVERTS_SUCCESS = 'adverts/FETCH_ADVERTS_SUCCESS';
const FETCH_ADVERTS_FAIL = 'adverts/FETCH_ADVERTS_FAIL';
const SEARCH_CRITERIA = 'advert/SEARCH_CRITERIA';

const initialState = {
  searchCriteria: {},
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
    case SEARCH_CRITERIA:
      return {
        ...state,
        searchCriteria: action.searchCriteria
      };
    default:
      return state;
  }
}

// Actions

export function setSearchCriteria(searchCriteria) {
  return {type: SEARCH_CRITERIA, searchCriteria};
}

export function fetchAdverts(searchCriteria) {
  const request = searchCriteria ? buildRequest(searchCriteria)  : "";
  return  {
    types: [FETCH_ADVERTS, FETCH_ADVERTS_SUCCESS, FETCH_ADVERTS_FAIL],
    promise: client => client
      .get(`/api/adverts?page=0&size=${PAGE_SIZE}${request}`)
  };
}

export const buildRequest = searchCriteria => {
  return Object.keys(searchCriteria).reduce((previous, current) => `${previous}&${current}=${searchCriteria[current]}`, "");
};
