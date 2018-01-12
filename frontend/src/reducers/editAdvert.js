
const EDIT_ADVERT = 'edit_advert/EDIT_ADVERT';
const EDIT_ADVERT_SUCCESS = 'edit_advert/EDIT_ADVERT_SUCCESS';
const EDIT_ADVERT_FAIL = 'edit_advert/EDIT_ADVERT_FAIL';

const initialState = {
  errorMessage: null,
};

// Reducer

export default function editProfileReducer(state = initialState, action) {
  switch (action.type) {
    case EDIT_ADVERT_FAIL:
      return {
        ...state,
        errorMessage: action.error.data.messageKey,
      };
    case EDIT_ADVERT_SUCCESS:
      return {
        ...state,
        errorMessage: null,
      };
    default:
      return state;
  }
}

// Actions

export function editAdvert(idAdvert, advert, closeModal, fetchAdvert) {
  return  {
    types: [EDIT_ADVERT, EDIT_ADVERT_SUCCESS, EDIT_ADVERT_FAIL],
    promise: (client) => client.put(`/api/updateAdvert/${idAdvert}`, advert),
    afterSuccess: (dispatch, getState, response) => {
      closeModal();
      fetchAdvert(idAdvert);
    }
  };
}

