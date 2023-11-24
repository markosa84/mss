import { addMinutes } from "date-fns";
import format from "date-fns/format";
import { useMemo } from "react";
import { useAppointmentSelector } from "../pages/BookAppointment/useAppointmentSelector";

const MAX_DAILY_SLOT_COUNT = 12;
const START_TIME = "09:00";
const SLOT_DURATION_MINS = 15;

export default function TimePicker() {
  const {
    selectedDate,
    selectedDoctorIds,
    dailyUnavailableSlots,
    departmentDoctors,
    selectedAppointment,
    setSelectedAppointment,
  } = useAppointmentSelector();
  const dailySlots = useMemo(() => {
    const firstSlotStartTime = new Date(
      `${format(selectedDate, "yyyy-MM-dd")}T${START_TIME}`
    );
    const slotArr = [];
    for (
      let i = 0, time = firstSlotStartTime;
      i < MAX_DAILY_SLOT_COUNT;
      i++, time = addMinutes(time, 15)
    ) {
      slotArr.push({ slotId: i + 1, startTime: time });
    }
    return slotArr;
  }, [selectedDate]);

  function isSlotAvailable(slotId, startTime, selectedDoctorId) {
    const unavailableSlotsObj = dailyUnavailableSlots.find(
      (daySlot) => daySlot.date === format(startTime, "yyyy-MM-dd")
    );
    if (!unavailableSlotsObj) {
      return true;
    }
    const unavailableDocs = unavailableSlotsObj.doctorsTimeSlots.find(
      (doc) => doc.doctorId === selectedDoctorId
    );
    if (!unavailableDocs) {
      return true;
    }
    return !unavailableDocs.unavailableSlotIds.includes(slotId);
  }

  function setClassName(slotId, startTime, selectedDoctorId) {
    const baseClassName = "choice";
    return isSlotAvailable(slotId, startTime, selectedDoctorId)
      ? baseClassName
      : `${baseClassName} disabled`;
  }

  if (selectedAppointment) console.log(selectedAppointment);

  return (
    <div className="table">
      <div className="column">
        <div className="header-cell"></div>
        {dailySlots.map((slot) => (
          <div key={slot.slotId}>
            {`${format(slot.startTime, "HH:mm")} - ${format(
              addMinutes(slot.startTime, SLOT_DURATION_MINS),
              "HH:mm"
            )}`}
          </div>
        ))}
      </div>
      {/* Rendering the timeslots */}
      {selectedDoctorIds.map((selectedDoctorId) => (
        <div key={selectedDoctorId} className="column">
          <div className="header-cell">
            {
              departmentDoctors.find((doc) => doc.doctorId === selectedDoctorId)
                .name
            }
          </div>
          {dailySlots.map((slot) => {
            return (
              <div
                key={slot.slotId}
                onClick={
                  isSlotAvailable(slot.slotId, slot.startTime, selectedDoctorId)
                    ? () =>
                        setSelectedAppointment({
                          doctorId: selectedDoctorId,
                          doctorName: departmentDoctors.find(
                            (doc) => doc.doctorId === selectedDoctorId
                          ).name,
                          appointmentDateTime: slot.startTime,
                          slotId: slot.slotId,
                          user: "Júz Erzsébet",
                        })
                    : () => {}
                }
                className={setClassName(
                  slot.slotId,
                  slot.startTime,
                  selectedDoctorId
                )}
              >
                {selectedAppointment?.doctorId === selectedDoctorId &&
                  selectedAppointment?.slotId === slot.slotId &&
                  "Selected"}
              </div>
            );
          })}
        </div>
      ))}
    </div>
  );
}
