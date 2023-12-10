import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { FormControl } from "../components/FormControl";
import { DevTool } from "@hookform/devtools";
import { axios1 } from "../api/axios";
import axios from "axios";
import { addYears, format } from "date-fns";

export const Register = () => {
  const [genders, setGenders] = useState([]);
  const [languages, setLanguages] = useState([]);
  const {
    register,
    trigger,
    getValues,
    setValue,
    handleSubmit,
    control,
    formState: { errors, isDirty, isValid },
  } = useForm({
    defaultValues: {
      firstName: "",
      lastName: "",
      email: "",
      password: "",
      confirmPassword: "",
      gender: "",
      dateOfBirth: "",
      mothersName: "",
      placeOfBirth: "",
      language: "",
      phoneNumber: "",
      TAJNumber: "",
    },
    mode: "onTouched",
  });

  useEffect(() => {
    async function fetchChoices() {
      try {
        const response = await axios1.get("/register");
        setGenders(response.data.gendersDto);
        setLanguages(response.data.languagesDto);
        setValue("language", response.data.languagesDto[0].language);
      } catch (error) {
        console.log(error);
      }
    }
    fetchChoices();
  }, []);

  async function onSubmit({ confirmPassword, ...rest }) {
    console.log(rest);
    try {
      const response = await axios1.post("/register/client", rest);
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  }

  function disableLetters(e) {
    const allowedChars = /[0-9\s-]/;
  }

  return (
    <div className="container">
      <form onSubmit={handleSubmit(onSubmit)} className="login-form" noValidate>
        <FormControl>
          <label htmlFor="firstName">First name</label>
          <input
            type="text"
            id="firstName"
            {...register("firstName", {
              required: {
                value: true,
                message: "First name is required!",
              },
              minLength: {
                value: 2,
                message: "First name cannot be shorter than 2 letters!",
              },
              pattern: {
                value: /^[A-ZÁÉÍÓÖŐÚÜŰ][a-záéíóöőúüű]+$/,
                message:
                  "First name must start with a capital letter and can only contain letters!",
              },
            })}
          />
          <p className="error-msg">{errors.firstName?.message}</p>
        </FormControl>

        <FormControl>
          <label htmlFor="lastName">Last name</label>
          <input
            type="text"
            id="lastName"
            {...register("lastName", {
              required: {
                value: true,
                message: "Last name is required!",
              },
              minLength: {
                value: 2,
                message: "Last name cannot be shorter than 2 letters!",
              },
              pattern: {
                value: /^[A-ZÁÉÍÓÖŐÚÜŰ][a-záéíóöőúüű]+$/,
                message:
                  "Last name must start with a capital letter and can only contain letters!",
              },
            })}
          />
          <p className="error-msg">{errors.lastName?.message}</p>
        </FormControl>

        <FormControl>
          <label htmlFor="gender">Gender</label>
          <select
            id="gender"
            value={getValues("gender")}
            {...register("gender", {
              required: {
                value: true,
                message: "You must select a gender!",
              },
            })}
          >
            <option value="">Please select gender</option>
            {genders.map((gender) => (
              <option key={gender.genderId} value={gender.gender}>
                {gender.gender}
              </option>
            ))}
          </select>
          <p className="error-msg">{errors.gender?.message}</p>
        </FormControl>

        <FormControl>
          <label htmlFor="dob">Date of birth</label>
          <input
            type="date"
            id="dob"
            {...register("dateOfBirth", {
              onChange: (e) => console.log(getValues("dateOfBirth")),
              required: {
                value: true,
                message: "Date of birth is required for registration!",
              },
              max: {
                value: format(addYears(new Date(), -18), "yyyy-MM-dd"),
                message:
                  "Only adult clients can use the services of the clinic!",
              },
            })}
          />
          <p className="error-msg">{errors.dateOfBirth?.message}</p>
        </FormControl>

        <FormControl>
          <label htmlFor="placeOfBirth">Place of birth</label>
          <input
            type="text"
            id="placeOfBirth"
            {...register("placeOfBirth", {
              required: {
                value: true,
                message: "Place of birth is required!",
              },
            })}
          />
          <p className="error-msg">{errors.placeOfBirth?.message}</p>
        </FormControl>

        <FormControl>
          <label htmlFor="motherName">Mother's name</label>
          <input
            type="text"
            id="mothersName"
            {...register("mothersName", {
              required: {
                value: true,
                message: "Mother's  name is required!",
              },
            })}
          />
          <p className="error-msg">{errors.mothersName?.message}</p>
        </FormControl>

        <FormControl>
          <label htmlFor="language">Preferred language</label>
          <select
            id="language"
            value={getValues("language")}
            {...register("language")}
          >
            {languages.map((language) => (
              <option key={language.languageId} value={language.language}>
                {language.language}
              </option>
            ))}
          </select>
        </FormControl>

        <FormControl>
          <label htmlFor="phoneNumber">Phone number</label>
          <input
            type="tel"
            id="phoneNumber"
            {...register("phoneNumber", {
              required: {
                value: true,
                message: "Phone number is required for registration",
              },
              pattern: {
                value:
                  /^(06|\+36)?[\s\-]?((1|20|30|31|50|70)[\s\-]?\d{3}[\s\-]?\d{4})$|((2[2-9]|3[1-7]|4[2,4-9]|5[1-7,9]|6[2,3,6,8,9]|7[1-9]|8[0,2-5,7-9]|9[2-6,9])[\s\-]?\d{3}[\s\-]?\d{3})$/,
                message: "Phone number is not in the required format",
              },
            })}
          />
          <p className="error-msg">{errors.phoneNumber?.message}</p>
        </FormControl>

        <FormControl>
          <label htmlFor="TAJNumber">Social Security Code (TAJ)</label>
          <input
            type="tel"
            id="TAJNumber"
            {...register("TAJNumber", {
              required: {
                value: true,
                message:
                  "Social Security Code (TAJ) is required for registration",
              },
              pattern: {
                value: /^\d{3}-\d{3}-\d{3}$/,
                message: "Invalid Social Security Code (TAJ)",
              },
            })}
          />
          <p className="error-msg">{errors.TAJNumber?.message}</p>
        </FormControl>

        <FormControl>
          <label htmlFor="email">Email address</label>
          <input
            type="text"
            id="email"
            {...register("email", {
              required: {
                value: true,
                message: "Email is required for registration!",
              },
              pattern: {
                value:
                  /^([a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\.)+[a-zA-Z]{2,7})$/,
                message: "Email format is incorrect",
              },
            })}
          />
          <p className="error-msg">{errors.email?.message}</p>
        </FormControl>

        <FormControl>
          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            {...register("password", {
              onChange: () => {
                if (getValues("confirmPassword") !== "")
                  trigger("confirmPassword");
              },
              required: {
                value: true,
                message: "Password is required for login",
              },
            })}
          />
          <p className="error-msg">{errors.password?.message}</p>
        </FormControl>
        <FormControl>
          <label htmlFor="confirmPassword">Confirm password</label>
          <input
            type="password"
            id="confirmPassword"
            {...register("confirmPassword", {
              validate: {
                dontMatch: (fieldValue) => {
                  return (
                    fieldValue === getValues("password") ||
                    "Passwords don't match!"
                  );
                },
              },
            })}
          />
          <p className="error-msg">{errors.confirmPassword?.message}</p>
        </FormControl>

        <button disabled={!isDirty || !isValid} className="submit-btn">
          Log in
        </button>
      </form>
      <DevTool control={control} />
    </div>
  );
};
