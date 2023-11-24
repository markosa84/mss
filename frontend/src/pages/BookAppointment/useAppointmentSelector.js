import { useContext } from "react";
import { AppointmentContext } from "./AppointmentProvider";

export function useAppointmentSelector() {
  const value = useContext(AppointmentContext);
  if (value === null) {
    throw new Error("Must be inside provider.");
  }
  return value;
}
