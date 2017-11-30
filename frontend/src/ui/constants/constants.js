import { FormFieldBuilder } from "../component/forms/FormFieldBuilder";

export const MENU_FOR_USER = [
  {label: 'Home', link: '/'},
  {label: 'Logout', link: '/logout'},
  {label: 'Profile', link: '/profile'},
  {label: 'Add Advert', link: '/new_advert'},
  {label: 'Terms Of Use', link: '/terms'}
];

export const MENU_FOR_GUEST = [
  {label: 'Home', link: '/'},
  {label: 'Register', link: '/register'},
  {label: 'Login', link: '/login'},
  {label: 'Terms Of Use', link: '/terms'}
];

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

export const terms = "Last updated: 2017-11-30 \n" +
  "Please read these Terms of Use (\"Terms\", \"Terms of Use\") " +
  "carefully before using the http://www.mywebsite.com (change this) " +
  "website My Company (change this) (\"us\", \"we\", or \"our\"). " +
  "\nYour access to and use of the Service is conditioned on your " +
  "acceptance of and compliance with these Terms. " +
  "\nThese Terms apply to all visitors, users and others " +
  "who access or use the Service." +
  "\nBy accessing or using the Service you agree to be bound by these Terms. " +
  "\nIf you disagree with any part of the terms then you may not access the Service.";
