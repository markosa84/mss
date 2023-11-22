import axios from "axios";
import { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import {
  setHours,
  setMinutes,
  setSeconds,
  format,
  addMinutes,
  isWeekend,
  nextMonday,
} from "date-fns";
import { useNavigate } from "react-router-dom";
import { af } from "date-fns/locale";

export const SelectDateAndTime = ({
  selectedDepartmentName,
  setSelectedDepartment,
}) => {
  const [selectedDate, setSelectedDate] = useState(() => {
    const today = new Date();

    return isWeekend(today) ? nextMonday(today) : today;
  });
  const [departmentDoctors, setDepartmentDoctors] = useState([]);
  const [selectedDoctorIds, setSelectedDoctorIds] = useState([]);
  const [timeSlots, setTimeSlots] = useState([]);
  const [fullSlotsState, setFullSlotsState] = useState([]);
  const [appointment, setAppointment] = useState();
  const navigate = useNavigate();

  const startTime = setSeconds(setMinutes(setHours(selectedDate, 9), 0), 0);
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
    setDepartmentDoctors((prevDepartmentDoctors) =>
      prevDepartmentDoctors.map((doctor) =>
        doctor.doctorId === doctorId
          ? { ...doctor, selected: !doctor.selected }
          : doctor
      )
    );
    setAppointment(undefined);
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

  const dateString = selectedDate.toISOString().substring(0, 10);

  useEffect(() => {
    if (timeSlots.length > 0) {
      const dateSlots = timeSlots.find(({ date }) => date === dateString);

      const fullSlots = dateSlots.availableSlotsDoctors
        .map((docSlot) => {
          const fullArr = [];
          for (let i = 0, p = i; i < 12; ) {
            if (docSlot.availableSlots[p] === i + 1) {
              fullArr.push({
                available: true,
                from: addMinutes(startTime, i * 15),
                selected: false,
              });
              i++;
              p++;
            } else {
              fullArr.push({
                available: false,
                from: addMinutes(startTime, i * 15),
                selected: false,
              });
              i++;
            }
          }
          return {
            doctorId: docSlot.doctorId,
            availableSlots: fullArr,
          };
        })
        .filter((slot) => selectedDoctorIds.includes(slot.doctorId));
      setFullSlotsState(fullSlots);
    }
  }, [timeSlots, selectedDoctorIds, selectedDate]);

  useEffect(() => {
    setSelectedDoctorIds(
      departmentDoctors
        .filter((doctor) => doctor.selected)
        .map((doctor) => doctor.doctorId)
    );
  }, [departmentDoctors]);

  function handleChoice(i, doctorId, startTime) {
    setFullSlotsState((prev) =>
      prev.map((slot) => {
        return {
          ...slot,
          availableSlots: slot.availableSlots.map((aSlot, j) => {
            return {
              ...aSlot,
              selected: i === j && slot.doctorId === doctorId,
            };
          }),
        };
      })
    );
    setAppointment({
      doctorId,
      doctorName: departmentDoctors.find((doc) => doc.doctorId === doctorId)
        .name,
      appointmentSlot: i,
      date: format(startTime, "yyyy-MM-dd"),
      startTime: format(startTime, "HH:mm"),
    });
  }

  function handleDateChange(date) {
    setSelectedDate(date);
    setAppointment(undefined);
  }

  function confirm() {
    console.log("Appointment confirmed", appointment);
    navigate("../appointment-booked");
  }

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
        <div>
          <h3 className="booking-instruction">2. Please select the date</h3>
          <div className="date-picker-wrapper">
            <DatePicker
              selected={selectedDoctorIds.length !== 0 && selectedDate}
              minDate={new Date()}
              maxDate={
                selectedDoctorIds.length === 0 &&
                new Date(Date.now() - 86_400_000)
              }
              onChange={handleDateChange}
              filterDate={isWeekday}
              excludeDates={getdisabledDates()}
              placeholderText="Select a date other than today or yesterday"
              calendarStartDay={1}
              shouldCloseOnSelect={false}
              inline={true}
            />
          </div>
        </div>
        <div>
          <h3 className="booking-instruction">3. Please select the time</h3>
          {fullSlotsState.length > 0 && (
            <div className="table">
              <div className="column">
                <div className="header-cell"></div>
                {fullSlotsState[0].availableSlots.map((slot) => (
                  <div key={slot.from}>
                    {format(slot.from, "HH:mm")} -{" "}
                    {format(addMinutes(slot.from, 15), "HH:mm")}
                  </div>
                ))}
              </div>
              {fullSlotsState.map((docSlot) => (
                <div key={docSlot.doctorId} className="column">
                  <div className="header-cell">
                    {
                      departmentDoctors.find(
                        (doc) => doc.doctorId === docSlot.doctorId
                      ).name
                    }
                  </div>
                  {docSlot.availableSlots.map((slot, i) => (
                    <div
                      onClick={
                        slot.available
                          ? () => handleChoice(i, docSlot.doctorId, slot.from)
                          : () => {}
                      }
                      key={slot.from}
                      className={slot.available ? "choice" : "choice disabled"}
                    >
                      {slot.selected ? "Selected" : ""}
                    </div>
                  ))}
                </div>
              ))}
            </div>
          )}
        </div>
        {appointment && (
          <div>
            <h3 className="booking-instruction">
              4. Please confirm your appointment
            </h3>
            <h4 className="appointment-summary">{`Your appointment is with ${appointment.doctorName}, on ${appointment.date} at ${appointment.startTime}.`}</h4>
            <button onClick={confirm} className="confirm-appointment-button">
              Confirm appointment
            </button>
          </div>
        )}
      </div>
    </div>
  );
};
