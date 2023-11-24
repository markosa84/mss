import { useState } from "react";
import { useAppointmentSelector } from "../pages/BookAppointment/useAppointmentSelector";

const IMG_URL_BASE = "/assets/dept-";

export const DepartmentCard = ({ department }) => {
  let [imgSrc, setImgSrc] = useState(`${IMG_URL_BASE}${department.name}.jpg`);

  return (
    <div className="card">
      <Header imgSrc={imgSrc} setImgSrc={setImgSrc} />
      <div className="card__content">
        <Body department={department} />
        <Buttons department={department} />
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

function Buttons({ department }) {
  const { setSelectedDepartmentId } = useAppointmentSelector();
  return (
    <div className="card__buttons">
      <button>Learn more</button>
      <button
        onClick={() => {
          setSelectedDepartmentId(department.areaOfExpertiseId);
        }}
      >
        Book Appointment
      </button>
    </div>
  );
}
