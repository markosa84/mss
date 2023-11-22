import axios from "axios";
import { useEffect, useState } from "react";

export const DepartmentCard = ({ department, setSelectedDepartment }) => {
  let [deptImgUrl, setDeptImgUrl] = useState();

  useEffect(() => {
    axios
      .get(`/assets/dept-${department.name}.jpg`)
      .then((res) => {
        setDeptImgUrl(department.name);
      })
      .catch((err) => {
        setDeptImgUrl("default");
      });
  }, []);

  return (
    <>
      {deptImgUrl && (
        <Card
          department={department}
          setSelectedDepartment={setSelectedDepartment}
          deptImgUrl={deptImgUrl}
        />
      )}
    </>
  );
};

function Card({ department, setSelectedDepartment, deptImgUrl }) {
  return (
    <div className="card">
      <div className="card__image">
        <img src={`/assets/dept-${deptImgUrl}.jpg`} alt="" />
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
}
