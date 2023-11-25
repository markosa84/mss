import "react-datepicker/dist/react-datepicker.css";
import { ConfirmSection } from "./ConfirmSection";
import { DatePickerSection } from "./DatePickerSection";
import { TimePickerSection } from "./TimePickerSection";
import { useAppointmentSelector } from "./useAppointmentSelector";
import { DoctorSelectorSection } from "./DoctorSelectorSection";

export function SelectDateAndTimeRevised({ selectedDepartmentName }) {
  const {
    selectedAppointment,
    setSelectedAppointment,
    setSelectedDepartmentId,
  } = useAppointmentSelector();

  function reset() {
    setSelectedDepartmentId(null);
    setSelectedAppointment(undefined);
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
