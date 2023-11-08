import axios from "axios";
import { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

export const SelectDateAndTime = ({
  selectedDepartmentName,
  setSelectedDepartment,
}) => {
  const [selectedDate, setSelectedDate] = useState(new Date());
  const [departmentDoctors, setDepartmentDoctors] = useState([]);
  const [selectedDoctorIds, setSelectedDoctorIds] = useState([]);

  const [timeSlots, setTimeSlots] = useState([]);

  // console.log(selectedDoctorIds);
  const isWeekday = (date) => {
    const day = date.getDay();
    return day !== 0 && day !== 6;
  };

  function getdisabledDates() {
    const disabledDates = [];

    for (let date of timeSlots) {
      const unavalableDoctors = [];
      for (let doctor of date.availableSlotsDoctors) {
        if (
          selectedDoctorIds.includes(doctor.doctorId) &&
          doctor.availableSlots.length === 0
        ) {
          unavalableDoctors.push(doctor);
        }
      }
      if (unavalableDoctors.length === selectedDoctorIds.length) {
        disabledDates.push(new Date(Date.parse(date.date)));
      }
    }
    return disabledDates;
  }

  function handleSelectChange(doctorId) {
    // console.log("changed doctor id: ", doctorId);
    setDepartmentDoctors((prevDepartmentDoctors) =>
      prevDepartmentDoctors.map((doctor) =>
        doctor.doctorId === doctorId
          ? { ...doctor, selected: !doctor.selected }
          : doctor
      )
    );
  }

  useEffect(() => {
    const fetchData = async () => {
      try {
        const doctorsResponse = await axios.get(
          "/dummyDb/doctorsCardiology.json"
        );
        setDepartmentDoctors(
          doctorsResponse.data.map((doctor) => ({ ...doctor, selected: true }))
        );
      } catch (error) {
        console.log(error);
      }
      const timeSlotResponse = await axios.get(
        "/dummyDb/doctorsTimeSlots.json"
      );
      setTimeSlots(timeSlotResponse.data);
    };
    fetchData();
  }, []);

  useEffect(() => {
    setSelectedDoctorIds(
      departmentDoctors
        .filter((doctor) => doctor.selected)
        .map((doctor) => doctor.doctorId)
    );
  }, [departmentDoctors]);

  return (
    <div>
      <h3 className="selected-department-heading">
        Selected department: <span>{selectedDepartmentName}</span>
        <button onClick={() => setSelectedDepartment(null)}>X</button>
      </h3>
      <div>
        <div>
          <h3 className="booking-instruction">1. Please select doctor(s)</h3>
          <ul className="doctors">
            {departmentDoctors.length > 0 &&
              departmentDoctors.map((doctor, i) => (
                <li key={doctor.doctorId}>
                  <input
                    id={doctor.name}
                    type="checkbox"
                    value={doctor.name}
                    checked={doctor.selected}
                    onChange={() => handleSelectChange(doctor.doctorId)}
                  />
                  <label htmlFor={doctor.name}>{doctor.name}</label>
                </li>
              ))}
          </ul>
        </div>
        <h3 className="booking-instruction">2. Please select the date</h3>
        <div className="date-picker-wrapper">
          <DatePicker
            selected={selectedDoctorIds.length !== 0 && selectedDate}
            minDate={new Date()}
            maxDate={
              selectedDoctorIds.length === 0 &&
              new Date(Date.now() - 86_400_000)
            }
            onChange={(date) => setSelectedDate(date)}
            filterDate={isWeekday}
            excludeDates={getdisabledDates()}
            placeholderText="Select a date other than today or yesterday"
            calendarStartDay={1}
            shouldCloseOnSelect={false}
            inline={true}
          />
        </div>
      </div>
    </div>
  );
};
