import TimePicker from "../../components/TimePicker";
import { SectionHeader } from "./SectionHeader";
import { useAppointmentSelector } from "./useAppointmentSelector";

export function TimePickerSection() {
  const { selectedDoctorIds } = useAppointmentSelector();
  return (
    <div>
      <SectionHeader>3. Please select the time</SectionHeader>
      {selectedDoctorIds.length > 0 && <TimePicker />}
    </div>
  );
}
