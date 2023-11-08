import { DepartmentCard } from "../components/DepartmentCard";

export const SelectDepartment = ({ departments, setSelectedDepartment }) => {
  return (
    <div>
      <h2 className="departments-title">Please select a department</h2>
      <div className="card-container">
        {departments.map((department) => (
          <DepartmentCard
            key={department.id}
            department={department}
            setSelectedDepartment={setSelectedDepartment}
          />
        ))}
      </div>
    </div>
  );
};
