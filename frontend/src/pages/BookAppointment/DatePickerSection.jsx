import { addDays, isWeekend } from "date-fns";
import { SectionHeader } from "./SectionHeader";
import DatePicker from "react-datepicker";
import { useAppointmentSelector } from "./useAppointmentSelector";

export function DatePickerSection() {
  const {
    selectedDoctorIds,
    selectedDate,
    getFirstAvailableDate,
    handleDateChange,
    disabledDateStrings,
  } = useAppointmentSelector();
  return (
    <div>
      <SectionHeader>1. Please select doctor(s)</SectionHeader>
      <div className="date-picker-wrapper">
        <DatePicker
          focusSelectedMonth
          selected={selectedDate}
          minDate={getFirstAvailableDate()}
          disabledKeyboardNavigation
          maxDate={
            selectedDoctorIds.length === 0 &&
            addDays(getFirstAvailableDate(), -1)
          }
          onChange={handleDateChange}
          filterDate={(date) => !isWeekend(date)}
          excludeDates={disabledDateStrings.map(
            (ds) => new Date(Date.parse(ds))
          )}
          calendarStartDay={1}
          shouldCloseOnSelect={false}
          inline={true}
        />
      </div>
    </div>
  );
}
