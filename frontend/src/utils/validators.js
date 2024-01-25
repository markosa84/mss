import { addYears, format } from "date-fns";

export const passwordValidator = {
  required: {
    value: true,
    message: "Password is required for login",
  },
  minLength: {
    value: 8,
    message: "Password must be at least 8 characters long",
  },
  maxLength: {
    value: 20,
    message: "Password cannot be longer than 20 characters",
  },
  validate: {
    containsNumber: (fieldValue) => {
      return (
        /.*[0-9]/.test(fieldValue) ||
        "Password must contain at least one number"
      );
    },
    containsLowerCase: (fieldValue) => {
      return (
        /.*[a-z]/.test(fieldValue) ||
        "Password must contain at least one lower case letter"
      );
    },
    containsUperCase: (fieldValue) => {
      return (
        /.*[A-Z]/.test(fieldValue) ||
        "Password must contain at least one upper case letter"
      );
    },
    containsSpecialCh: (fieldValue) => {
      return (
        /.*[!@#&()\-[\]{}:;',?/*~$^+=<>]/.test(fieldValue) ||
        "Password must contain at least one special character"
      );
    },
  },
};

export const emailValidator = {
  required: {
    value: true,
    message: "Email is required for login",
  },
  pattern: {
    value:
      /^([a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\.)+[a-zA-Z]{2,7})$/,
    message: "Email format is incorrect",
  },
};

export const firstnameValidator = {
  required: {
    value: true,
    message: "First name is required!",
  },
  minLength: {
    value: 2,
    message: "First name cannot be shorter than 2 letters!",
  },
  pattern: {
    value: /^([a-záéíóöőúüű]+\s?)+$/i,
    message: "First name can only contain letters",
  },
};

export const lastnameValidator = {
  required: {
    value: true,
    message: "Last name is required!",
  },
  minLength: {
    value: 2,
    message: "Last name cannot be shorter than 2 letters!",
  },
  pattern: {
    value: /^[a-záéíóöőúüű\-]+$/i,
    message:
      "Last name must start with a capital letter and can only contain letters!",
  },
};

export const dateOfBirthValidator = {
  // onChange: (e) => console.log(getValues("dateOfBirth")),
  required: {
    value: true,
    message: "Date of birth is required for registration!",
  },
  max: {
    value: format(addYears(new Date(), -18), "yyyy-MM-dd"),
    message: "Only adult clients can use the services of the clinic!",
  },
};
