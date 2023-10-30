import React, { useState } from "react";
import { axios1, axiosDummy } from "../api/axios";
import { FormControl } from "../components/FormControl";
import { useForm } from "react-hook-form";
import { DevTool } from "@hookform/devtools";

export const Login = () => {
  const {
    register,
    handleSubmit,
    control,
    formState: { errors, isDirty, isValid },
  } = useForm({
    defaultValues: {
      email: "",
      login: "",
    },
    mode: "onTouched",
  });

  // for checking fetch with dummyjson.com
  // const dummyUser = { username: "kminchelle", password: "0lelplR" };
  // --------------------------------

  const onSubmit = async ({ email, password }) => {
    const loginData = { email, password };
    try {
      const res = await axios1.post("/login", loginData, {
        withCredentials: true, // Hozzáadott withCredentials opció
      });
      console.log("Zseniális!!! Megvan a user!");
      console.log("User: ", res.data);
    } catch (error) {
      console.log("Valami nem jó...");
      console.log("Error: ", error);
    }

};

  return (
    <div className="container">
      <form onSubmit={handleSubmit(onSubmit)} className="login-form" noValidate>
        <FormControl>
          <label htmlFor="email">Email address</label>
          <input
            type="text"
            id="email"
            {...register("email", {
              required: {
                value: true,
                message: "Email is required for login",
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
              required: {
                value: true,
                message: "Password is required for login",
              },
            })}
          />
          <p className="error-msg">{errors.password?.message}</p>
        </FormControl>

        <button disabled={!isDirty || !isValid} className="submit-btn">
          Log in
        </button>
      </form>
      <DevTool control={control} />
    </div>
  );
};
