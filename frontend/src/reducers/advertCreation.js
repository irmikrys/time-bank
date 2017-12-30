import { browserHistory } from 'react-router';
import {fetchAdverts} from "./adverts";

const CREATE_ADVERT = 'CREATE_ADVERT';
const CREATE_ADVERT_SUCCESS = 'CREATE_ADVERT_SUCCESS';
const CREATE_ADVERT_FAIL = 'CREATE_ADVERT_FAIL';

const initialState = {
  createAdvertSuccess: false
};

// Reducer

export default function advertCreationReducer(state = initialState, action) {
  switch (action.type) {
    case CREATE_ADVERT_SUCCESS:
      return {
        ...state,
        createAdvertSuccess: true
      };
    default:
      return state;
  }
}

// Actions

export function createAdvert(advert) {
  return  {
    types: [CREATE_ADVERT, CREATE_ADVERT_SUCCESS, CREATE_ADVERT_FAIL],
    promise: (client) => client.post('/api/advert', advert),
    afterSuccess: (dispatch, getState, result) => {
      dispatch(fetchAdverts());
      browserHistory.push('/');
    }
  };
}
