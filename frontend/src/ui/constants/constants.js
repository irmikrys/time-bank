import { FormFieldBuilder } from "../component/forms/FormFieldBuilder";
import React from 'react'
import { Link } from "react-router";

export const PAGE_SIZE = 6;

export const MENU_FOR_GUEST = [
  {label: 'Home', link: '/'},
  {label: 'Register', link: '/register'},
  {label: 'Login', link: '/login'}
];

export const MENU_FOR_USER = [
  {label: 'Home', link: '/'},
  {label: 'Logout', link: '/logout'},
  {label: 'Profile', link: `/profile`},
  {label: 'Add Advert', link: '/new_advert'},
  {label: 'Categories', link: '/categories'}
];

export const FOOTER = [
  {label: 'Terms Of Use', link: '/terms'},
  {label: 'About Service', link: '/about'},
  {label: 'Contact', link: '/contact'}
];

export const MY_ADVERTS = 'My Adverts';
export const INTERESTING = 'Interesting';
export const ARCHIVED = 'Archived';

export const USERNAME = "username";
export const PASSWORD = "password";
export const FIRST_NAME = "firstName";
export const LAST_NAME = "lastName";
export const EMAIL = "email";
export const LOCATION = "location";

export const getFormField = fieldKey => FORM_FIELDS.get(fieldKey);

const FORM_FIELDS = new Map([
  [FIRST_NAME, new FormFieldBuilder()
    .withName(FIRST_NAME)
    .withPlaceholder("first name")
    .withType("text")
    .withPattern("[A-Za-z]{3,30}")
    .build()
  ],
  [LAST_NAME, new FormFieldBuilder()
    .withName(LAST_NAME)
    .withPlaceholder("last name")
    .withType("text")
    .withPattern("[A-Za-z]{3,30}")
    .build()
  ],
  [EMAIL, new FormFieldBuilder()
    .withName(EMAIL)
    .withPlaceholder("email")
    .withType("email")
    .build()
  ],
  [USERNAME, new FormFieldBuilder()
    .withName(USERNAME)
    .withPlaceholder("username")
    .withType("text")
    .withPattern("[A-Za-z][A-Za-z0-9]{3,14}")
    .build()
  ],
  [PASSWORD, new FormFieldBuilder()
    .withName(PASSWORD)
    .withPlaceholder("password")
    .withType("password")
    .withPattern("[A-Za-z0-9]{6,15}")
    .build()
  ]
]);

export const ADVERT_TYPE_SEEK = "SEEK";
export const ADVERT_TYPE_OFFER = "OFFER";

export const TYPE_SELECT_OPTIONS = [
  {value: ADVERT_TYPE_SEEK, label: ADVERT_TYPE_SEEK},
  {value: ADVERT_TYPE_OFFER, label: ADVERT_TYPE_OFFER}
];

export const SORT_BY_SELECT_OPTIONS = [
  {value: "title", label: `${String.fromCharCode(8593)} Title`},
  {value: "title,desc", label: `${String.fromCharCode(8595)} Title`},
  {value: "createDate", label: `${String.fromCharCode(8593)} Create Date`},
  {value: "createDate,desc", label: `${String.fromCharCode(8595)} Create Date`},
];

const COLORS = ['#EC407A', '#EF5350', '#AB47BC', '#7E57C2', '#5C6BC0', '#42A5F5', '#29B6F6', '#26C6DA', '#26A69A', '#66BB6A', '#9CCC65', '#EF6C00'];

const HEIGHTS = [420, 440, 460, 480];

const getRandomElement = array => array[Math.floor(Math.random() * array.length)];

export const addColorAndHeight = item => {
  return {
    color: getRandomElement(COLORS),
    height: `${getRandomElement(HEIGHTS)}px`,
    advert: item
  }
};

export const addColor = item => {
  return {
    color: getRandomElement(COLORS),
    category: item
  }
};

export const renderText = text => {
  return(
    text.split("\n").map(i => {
      return <p>{i}</p>;
    }))
};

export const PET_CARE = 1;
export const COOKING = 2;
export const HOUSEKEEPING = 3;
export const TUTORING = 4;
export const MAKEUP = 5;
export const FREE_TIME = 6;
export const PEOPLE_CARE = 7;
export const AMUSEMENT = 8;

export const categoryToGlyph = category => {
  switch (category) {
    case PET_CARE: return "glyphicon glyphicon-grain";
    case COOKING: return "glyphicon glyphicon-cutlery";
    case HOUSEKEEPING: return "glyphicon glyphicon-home";
    case TUTORING: return "glyphicon glyphicon-education";
    case MAKEUP: return "glyphicon glyphicon-eye-open";
    case FREE_TIME: return "glyphicon glyphicon-sunglasses";
    case PEOPLE_CARE: return "glyphicon glyphicon-user";
    case AMUSEMENT: return "glyphicon glyphicon-glass";
  }
};
