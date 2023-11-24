import { DepartmentCard } from "../../components/DepartmentCard";
import { useAppointmentSelector } from "./useAppointmentSelector";

export const SelectDepartment = () => {
  const { departments, setSelectedDepartmentId } = useAppointmentSelector();

  return (
    <div>
      <h2 className="departments-title">Please select a department</h2>
      <div className="card-container">
        {departments.map((department) => (
          <DepartmentCard
            key={department.areaOfExpertiseId}
            department={department}
          />
        ))}
      </div>
    </div>
  );
};
