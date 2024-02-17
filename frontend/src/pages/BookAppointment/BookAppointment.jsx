import { useEffect } from "react";
import { SelectDepartment } from "./SelectDepartment";
import axios from "axios";
import { SelectDateAndTime } from "./SelectDateAndTime";
import { SelectDateAndTimeRevised } from "./SelectDateAndTimeRevised";
import { useAppointmentSelector } from "./useAppointmentSelector";
import { AppointmentProvider } from "./AppointmentProvider";
import { axios1 } from "../../api/axios";
import { useAuth } from "../../Context/useAuth";

const InnerComponent = () => {
  const { auth } = useAuth();
  const {
    departments,
    setDepartments,
    selectedDepartmentId,
    setSelectedDepartmentId,
  } = useAppointmentSelector();

  useEffect(() => {
    axios1
      .get("/components/departmentcard", {
        headers: { Authorization: auth.accessToken },
      })
      .then((res) => setDepartments(res.data))
      .catch((err) => console.log(err));
  }, []);

  return selectedDepartmentId === null || departments.length === 0 ? (
    <SelectDepartment setSelectedDepartmentId={setSelectedDepartmentId} />
  ) : (
    <SelectDateAndTimeRevised
      selectedDepartmentName={
        departments.find((d) => d.areaOfExpertiseId === selectedDepartmentId)
          .name
      }
    />
  );
};

export const BookAppointment = () => {
  return (
    <AppointmentProvider>
      <InnerComponent />
    </AppointmentProvider>
  );
};
