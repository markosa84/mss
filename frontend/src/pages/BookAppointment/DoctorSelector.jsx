import { useAppointmentSelector } from "./useAppointmentSelector";

export function DoctorSelector() {
  const { departmentDoctors, selectedDoctorIds, handleSelectChange } =
    useAppointmentSelector();
  return (
    <ul className="doctors">
      {departmentDoctors.length > 0 &&
        departmentDoctors.map((doctor) => (
          <li key={doctor.doctorId}>
            <input
              id={doctor.name}
              type="checkbox"
              value={doctor.name}
              checked={selectedDoctorIds.includes(doctor.doctorId)}
              onChange={() => handleSelectChange(doctor.doctorId)}
            />
            <label htmlFor={doctor.name}>{doctor.name}</label>
          </li>
        ))}
    </ul>
  );
}
