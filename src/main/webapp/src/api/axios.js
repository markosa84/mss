import axios from "axios";

const axiosDummy = axios.create({
  baseURL: "https://dummyjson.com/auth",
});

const axios1 = axios.create({
  baseURL: "http://localhost:8080",
   withCredentials: true,
});

export { axios1, axiosDummy };
