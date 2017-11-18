import React from "react";

export const LabeledInput = props => (
  <div>
    <label>
      {props.label}
    </label>
    <input {...props}/>
  </div>
);
