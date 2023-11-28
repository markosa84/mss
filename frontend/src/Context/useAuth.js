import { useContext } from "react";
import { AuthContext } from "./AuthProvider";

export function useAuth() {
  const value = useContext(AuthContext);
  if (!value) {
    throw new Error("Must be used inside provider.");
  }
  return value;
}
