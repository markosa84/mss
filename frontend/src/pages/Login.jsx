import React, { useState } from "react";
import { axios1, axiosDummy } from "../api/axios";
import { FormControl } from "../components/FormControl";
import { useForm } from "react-hook-form";
import { DevTool } from "@hookform/devtools";
import { useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "../Context/useAuth";
import { af } from "date-fns/locale";
import { emailValidator, passwordValidator } from "../utils/validators";

export const Login = () => {
  const navigate = useNavigate();
  const { setAuth } = useAuth();
  const location = useLocation();
  const from = location.state?.from || "/dashboard";
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
    try {
      const res = await axios1.post("/login", { username: email, password });
      console.log("Zseniális!!! Megvan a user!");
      console.log("User: ", res);
      console.log(res.headers.authorization);
      setAuth({
        username: email,
        password,
        name: res.data.name,
        roles: [...res.data.roles],
        accessToken: res.headers.authorization,
        userId: res.data.userId,
      });
      navigate(from, { replace: true });
      // localStorage.setItem("mssAuth", res.headers.authorization);
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
            {...register("email", emailValidator)}
          />
          <p className="error-msg">{errors.email?.message}</p>
        </FormControl>

        <FormControl>
          <label htmlFor="password">Password</label>
          <input
            type="text"
            id="password"
            {...register("password", passwordValidator)}
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
