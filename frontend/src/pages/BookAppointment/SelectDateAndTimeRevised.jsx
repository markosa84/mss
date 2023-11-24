import "react-datepicker/dist/react-datepicker.css";
import { ConfirmSection } from "./ConfirmSection";
import { DatePickerSection } from "./DatePickerSection";
import { TimePickerSection } from "./TimePickerSection";
import { useAppointmentSelector } from "./useAppointmentSelector";
import { DoctorSelectorSection } from "./DoctorSelectorSection";

export function SelectDateAndTimeRevised({ selectedDepartmentName }) {
  const {
    selectedAppointment,
    setSelectedDepartmentId,
    setDepartmentDoctors,
    setSelectedDate,
    setSelectedDoctorIds,
  } = useAppointmentSelector();

  function reset() {
    setSelectedDepartmentId(null);
    // setSelectedDoctorIds([]);
    // setDepartmentDoctors([]);
    // setSelectedDate(null);
  }

  return (
    <div>
      <h3 className="selected-department-heading">
        Selected department: <span>{selectedDepartmentName}</span>
        <button onClick={reset}>X</button>
      </h3>
      <div>
        <DoctorSelectorSection />
        <DatePickerSection />
        <TimePickerSection />
        {selectedAppointment && (
          <ConfirmSection appointment={selectedAppointment} />
        )}
      </div>
    </div>
  );
}
