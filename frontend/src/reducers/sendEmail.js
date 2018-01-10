const SEND_EMAIL = 'send_email/SEND_EMAIL';
const SEND_EMAIL_SUCCESS = 'send_email/SEND_EMAIL_SUCCESS';
const SEND_EMAIL_FAIL = 'send_email/SEND_EMAIL_FAIL';

const initialState = {
  errorMessage: null,
};

// Reducer

export default function sendEmailReducer(state = initialState, action) {
  switch (action.type) {
    case SEND_EMAIL_FAIL:
      return {
        ...state,
        errorMessage: action.error.data.messageKey,
      };
    case SEND_EMAIL_SUCCESS:
      return {
        ...state,
        errorMessage: null,
      };
    default:
      return state;
  }
}

// Actions

export function sendEmail(email) {
  return  {
    types: [SEND_EMAIL, SEND_EMAIL_SUCCESS, SEND_EMAIL_FAIL],
    promise: (client) => client.post("/api/sendEmail", email),
  };
}

