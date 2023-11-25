import { addMinutes } from "date-fns";
import format from "date-fns/format";
import { useMemo } from "react";
import { useAppointmentSelector } from "../pages/BookAppointment/useAppointmentSelector";

export default function TimePicker() {
  const {
    selectedDate,
    selectedDoctorIds,
    dailyUnavailableSlots,
    departmentDoctors,
    selectedAppointment,
    setSelectedAppointment,
    dailySchedule,
  } = useAppointmentSelector();

  const dailySlots = useMemo(() => {
    return dailySchedule.map((slot) => ({
      slotId: slot.slotId,
      startTime: new Date(
        `${format(selectedDate, "yyyy-MM-dd")}T${slot.startTime}`
      ),
      endTime: new Date(
        `${format(selectedDate, "yyyy-MM-dd")}T${slot.endTime}`
      ),
    }));
  }, [selectedDate]);

  // const dailySlots = useMemo(() => {
  //   const firstSlotStartTime = new Date(
  //     `${format(selectedDate, "yyyy-MM-dd")}T${START_TIME}`
  //   );
  //   const slotArr = [];
  //   for (
  //     let i = 0, time = firstSlotStartTime;
  //     i < MAX_DAILY_SLOT_COUNT;
  //     i++, time = addMinutes(time, 15)
  //   ) {
  //     slotArr.push({ slotId: i + 1, startTime: time });
  //   }
  //   return slotArr;
  // }, [selectedDate]);

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

  return (
    <div className="table">
      <div className="column">
        <div className="header-cell"></div>
        {dailySlots.map((slot) => (
          <div key={slot.slotId}>
            {`${format(slot.startTime, "HH:mm")} - ${format(
              slot.endTime,
              "HH:mm"
            )}`}
          </div>
        ))}
      </div>
      {/* Rendering the available timeslots */}
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
