import axios from "axios";
import { addDays, format, isWeekend, nextMonday } from "date-fns";
import { createContext, useEffect, useState } from "react";

export const AppointmentContext = createContext();

const MAX_DAILY_SLOT_COUNT = 12;

export const AppointmentProvider = ({ children }) => {
  const [departments, setDepartments] = useState([]);
  const [selectedDepartmentId, setSelectedDepartmentId] = useState(null);
  const [selectedDate, setSelectedDate] = useState(() => {
    const today = new Date();
    return isWeekend(today) ? nextMonday(today) : today;
  });
  const [departmentDoctors, setDepartmentDoctors] = useState([]);
  const [dailyUnavailableSlots, setDailyUnavaliableSlots] = useState([]);
  const [selectedDoctorIds, setSelectedDoctorIds] = useState([]);
  const [selectedAppointment, setSelectedAppointment] = useState();

  useEffect(() => {
    console.log("should fetch here");
    const fetchData = async () => {
      try {
        const doctorsResponse = await axios.get(
          "/dummyDb/doctorsCardiology.json"
        );
        setDepartmentDoctors(doctorsResponse.data);
        setSelectedDoctorIds(
          doctorsResponse.data.map((doctor) => doctor.doctorId)
        );
      } catch (error) {
        console.log(error);
      }

      try {
        const timeSlotsResponse = await axios.get(
          "/dummyDb/allUnavailableTimeSlotsSmall.json"
        );
        setDailyUnavaliableSlots(timeSlotsResponse.data);
      } catch (error) {
        // console.log(error);
      }
    };
    fetchData();
  }, [selectedDepartmentId]);

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
            MAX_DAILY_SLOT_COUNT
            ? count + 1
            : count;
        },
        0
      );
      return unavailableDoctorCount === selectedDoctorIds.length;
    });
    disabledDateStrings = disabledDays.map((dayObj) => dayObj.date);
  }

  function getFirstAvailableDate() {
    let candidateDate = new Date();
    // if (!disabledDateStrings)
    //   return !isWeekend(candidateDate)
    //     ? candidateDate
    //     : nextMonday(candidateDate);
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
    console.log(date);
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
      }}
    >
      {children}
    </AppointmentContext.Provider>
  );
};
