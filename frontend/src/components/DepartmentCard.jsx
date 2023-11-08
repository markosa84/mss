export const DepartmentCard = ({ department, setSelectedDepartment }) => {
  return (
    <div className="card">
      <div className="card__image">
        <img src={`/assets/dept-${department.name}.jpg`} alt="" />
      </div>
      <div className="card__content">
        <div>
          <h3 className="card__title">{department.name}</h3>
          <p>{department.intro}</p>
        </div>
        <div className="card__buttons">
          <button>Learn more</button>
          <button
            onClick={() => {
              setSelectedDepartment({ ...department });
            }}
          >
            Book Appointment
          </button>
        </div>
      </div>
    </div>
  );
};
