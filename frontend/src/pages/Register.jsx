import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { FormControl } from "../components/FormControl";
import { DevTool } from "@hookform/devtools";
import { axios1 } from "../api/axios";
import { addYears, format } from "date-fns";
import { useNavigate } from "react-router-dom";
import {
  dateOfBirthValidator,
  emailValidator,
  firstnameValidator,
  lastnameValidator,
  passwordValidator,
} from "../utils/validators";

export const Register = () => {
  const navigate = useNavigate();
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
      tajNumber: "",
    },
    mode: "onTouched",
  });

  console.log(languages);

  useEffect(() => {
    async function fetchChoices() {
      const baseUrl = "/register";
      const urls = [`${baseUrl}/genders`, `${baseUrl}/languages`];

      try {
        const [genders, languages] = await Promise.all(
          urls.map((url) => axios1.get(url))
        );
        setGenders(genders.data);
        setLanguages(languages.data);
        setValue("language", languages.data[0].language);
      } catch (error) {
        console.log(error);
      }
    }
    fetchChoices();
  }, []);

  async function onSubmit({ confirmPassword, gender, language, ...rest }) {
    rest.firstName = rest.firstName
      .trim()
      .replace(/\s+/, " ")
      .toLowerCase()
      .replace(/\b\w/g, (match) => match.toUpperCase());
    rest.genderId = genders.find((gen) => gen.gender === gender).genderId;
    rest.languageId = [
      languages.find((lan) => lan.language === language).languageId,
    ];
    console.log(rest);
    try {
      const response = await axios1.post("/register/client", rest);
      console.log(response);
      navigate("../email-sent");
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
            {...register("firstName", firstnameValidator)}
          />
          <p className="error-msg">{errors.firstName?.message}</p>
        </FormControl>

        <FormControl>
          <label htmlFor="lastName">Last name</label>
          <input
            type="text"
            id="lastName"
            {...register("lastName", lastnameValidator)}
          />
          <p className="error-msg">{errors.lastName?.message}</p>
        </FormControl>

        <FormControl>
          <label htmlFor="gender">Gender</label>
          <select
            id="gender"
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
            {...register("dateOfBirth", dateOfBirthValidator)}
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
          <select id="language" {...register("language")}>
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
          <label htmlFor="tajNumber">Social Security Code (TAJ)</label>
          <input
            type="tel"
            id="tajNumber"
            {...register("tajNumber", {
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
          <p className="error-msg">{errors.tajNumber?.message}</p>
        </FormControl>

        <FormControl>
          <label htmlFor="email">Email address</label>
          <input
            type="text"
            id="email"
            {...register("email", emailValidator)}
          />
          <p className="error-msg">{errors.email?.message}</p>
        </FormControl>

        <FormControl>
          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            {...register("password", passwordValidator)}
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
          Register
        </button>
      </form>
      <DevTool control={control} />
    </div>
  );
};
