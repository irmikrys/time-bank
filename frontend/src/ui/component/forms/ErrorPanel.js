import React from "react";
import {getTranslatedErrorMessage} from "../../constants/errorMessages";

export const ErrorPanel = ({messageKey}) => (
  <p>{getTranslatedErrorMessage(messageKey)}</p>
);
