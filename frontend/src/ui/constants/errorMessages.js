const LOGIN_ERROR_BAD_LOGIN = "login.error.badLogin";
const LOGIN_ERROR_UNAUTHORIZED = "login.error.unauthorized";
const LOGIN_ERROR_PRIVATE = "login.error.private";
const REGISTER_ERROR_USERNAME_EXISTS = "register.error.usernameExists";
const REGISTER_ERROR_EMAIL_EXISTS = "register.error.emailExists";

const ERRORS_MAP = new Map([
  [LOGIN_ERROR_BAD_LOGIN, "Bad login or password!"],
  [LOGIN_ERROR_UNAUTHORIZED, "You must be logged in first!"],
  [LOGIN_ERROR_PRIVATE, "You must be logged in first!"],
  [REGISTER_ERROR_USERNAME_EXISTS, "Username already exists!"],
  [REGISTER_ERROR_EMAIL_EXISTS, "Username already exists!"]
]);

export const getTranslatedErrorMessage = messageKey => ERRORS_MAP.get(messageKey);
