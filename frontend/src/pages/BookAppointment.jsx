import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { SelectDepartment } from "./SelectDepartment";
import axios from "axios";
import { SelectDateAndTime } from "./SelectDateAndTime";

export const BookAppointment = () => {
  const [departments, setDepartments] = useState([]);
  const [selectedDepartment, setSelectedDepartment] = useState(null);
  const { search } = useLocation();
  const searchParams = new URLSearchParams(search);

  useEffect(() => {
    axios
      .get("/dummyDb/departments.json")
      .then((res) => setDepartments(res.data))
      .catch((err) => console.log(err));
  }, []);

  if (selectedDepartment === null && departments.length > 0) {
    return (
      <SelectDepartment
        departments={departments}
        setSelectedDepartment={setSelectedDepartment}
      />
    );
  }

  return (
    <SelectDateAndTime
      selectedDepartmentName={selectedDepartment?.name}
      setSelectedDepartment={setSelectedDepartment}
    />
  );
};
