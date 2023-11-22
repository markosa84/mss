import { useState } from "react";

const IMG_URL_BASE = "/assets/dept-";

export const DepartmentCard = ({ department, setSelectedDepartment }) => {
  let [imgSrc, setImgSrc] = useState(`${IMG_URL_BASE}${department.name}.jpg`);

  return (
    <div className="card">
      <Header imgSrc={imgSrc} setImgSrc={setImgSrc} />
      <div className="card__content">
        <Body department={department} />
        <Buttons
          department={department}
          setSelectedDepartment={setSelectedDepartment}
        />
      </div>
    </div>
  );
};

function Header({ imgSrc, setImgSrc }) {
  return (
    <div className="card__image">
      <img
        src={imgSrc}
        onError={() => setImgSrc(`${IMG_URL_BASE}default.jpg`)}
      />
    </div>
  );
}

function Body({ department }) {
  return (
    <div>
      <h3 className="card__title">{department.name}</h3>
      <p>{department.description}</p>
    </div>
  );
}

function Buttons(department, setSelectedDepartment) {
  return (
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
  );
}
