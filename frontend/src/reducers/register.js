import { browserHistory } from 'react-router';

const REGISTER = 'register/REGISTER';
const REGISTER_SUCCESS = 'register/REGISTER_SUCCESS';
const REGISTER_FAIL = 'register/REGISTER_FAIL';

const initialState = {
  errorMessage: null
};

// Reducer

export default function registerReducer(state = initialState, action) {
  switch (action.type) {
    case REGISTER_SUCCESS:
      return {
        ...state,
        errorMessage: action.result.data
      };
    default:
      return state;
  }
}

// Actions

export function register(username, password) {
  return  {
    types: [REGISTER, REGISTER_SUCCESS, REGISTER_FAIL],
    promise: (client) => client.post('/api/register', {username, password}),
    afterSuccess: () => {
      browserHistory.push('');
    }
  };
}
