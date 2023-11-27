import axios from "axios";
import { addDays, format, isWeekend, nextMonday, setMinutes } from "date-fns";
import { createContext, useEffect, useState } from "react";
import { axios1 } from "../../api/axios";

export const AppointmentContext = createContext();

export const AppointmentProvider = ({ children }) => {
  const [departments, setDepartments] = useState([]);
  const [departmentDoctors, setDepartmentDoctors] = useState([]);
  const [dailyUnavailableSlots, setDailyUnavaliableSlots] = useState([]);
  const [dailySchedule, setDailySchedule] = useState([]);

  const [selectedDepartmentId, setSelectedDepartmentId] = useState(null);
  const [selectedDate, setSelectedDate] = useState(() => {
    const today = new Date();
    return isWeekend(today) ? nextMonday(today) : today;
  });
  const [selectedDoctorIds, setSelectedDoctorIds] = useState([]);
  const [selectedAppointment, setSelectedAppointment] = useState();

  // Date and time picker date initialization
  useEffect(() => {
    if (selectedDepartmentId) {
      const fetchData = async () => {
        console.log(selectedDepartmentId);
        let endpoints = [
          "/dummyDb/doctorsCardiology.json",
          "/dummyDb/allUnavailableTimeSlotsSmall.json",
          "/dummyDb/dailySchedule.json",
        ];
        try {
          const apiDoctorRes = await axios1.get(
            `/doctors/area-of-expertise/${selectedDepartmentId}`,
            {
              headers: { Authorization: localStorage.getItem("mssAuth") },
            }
          );
          console.log(apiDoctorRes.data);
          const [doctors, unavailableSlots, schedule] = await Promise.all(
            endpoints.map((endpoint) => axios.get(endpoint))
          );
          setDepartmentDoctors(apiDoctorRes.data);
          setSelectedDoctorIds(
            apiDoctorRes.data.map((doctor) => doctor.doctorId)
          );
          // setDepartmentDoctors(apiDoctorRes.data);
          // setSelectedDoctorIds(
          //   apiDoctorRes.data.map((doctor) => doctor.doctorId)
          // );
          setDailyUnavaliableSlots(unavailableSlots.data);
          setDailySchedule(schedule.data);
        } catch (error) {
          console.log(error);
        }
      };

      fetchData();
    }
  }, [selectedDepartmentId]);

  // Reset selected date
  useEffect(() => {
    setSelectedDate(getFirstAvailableDate());
  }, [dailyUnavailableSlots, selectedDoctorIds]);
  let disabledDateStrings = [];
  if (dailyUnavailableSlots && selectedDoctorIds.length > 0) {
    const disabledDays = dailyUnavailableSlots.filter((day) => {
      const unavailableDoctorCount = selectedDoctorIds.reduce(
        (count, doctorId) => {
          const unavailableDoctor = day.doctorsTimeSlots.find(
            (doc) => doc.doctorId === doctorId
          );
          return unavailableDoctor?.unavailableSlotIds.length ===
            dailySchedule.length
            ? count + 1
            : count;
        },
        0
      );
      return unavailableDoctorCount === selectedDoctorIds.length;
    });
    disabledDateStrings = disabledDays.map((dayObj) => dayObj.date);
  }
  console.log(selectedDoctorIds);
  console.log(disabledDateStrings);

  function getFirstAvailableDate() {
    let candidateDate = new Date();
    while (disabledDateStrings.includes(format(candidateDate, "yyyy-MM-dd"))) {
      candidateDate = addDays(candidateDate, 1);
    }
    return !isWeekend(candidateDate)
      ? candidateDate
      : nextMonday(candidateDate);
  }

  function handleSelectChange(doctorId) {
    setSelectedDoctorIds((prevSelectedDoctorIds) => {
      if (selectedDoctorIds.includes(doctorId)) {
        return prevSelectedDoctorIds.filter(
          (selectedDoctorId) => selectedDoctorId !== doctorId
        );
      }
      return [...prevSelectedDoctorIds, doctorId].sort();
    });
    setSelectedAppointment(undefined);
  }

  function handleDateChange(date) {
    setSelectedDate(date);
    setSelectedAppointment(undefined);
  }

  return (
    <AppointmentContext.Provider
      value={{
        departmentDoctors,
        setDepartmentDoctors,
        dailyUnavailableSlots,
        setDailyUnavaliableSlots,
        selectedDate,
        setSelectedDate,
        selectedDoctorIds,
        setSelectedDoctorIds,
        selectedAppointment,
        setSelectedAppointment,
        disabledDateStrings,
        getFirstAvailableDate,
        handleSelectChange,
        handleDateChange,
        departments,
        setDepartments,
        selectedDepartmentId,
        setSelectedDepartmentId,
        dailySchedule,
      }}
    >
      {children}
    </AppointmentContext.Provider>
  );
};
