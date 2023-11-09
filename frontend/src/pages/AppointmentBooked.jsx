import { af } from "date-fns/locale";
import { useNavigate } from "react-router-dom";

export const AppointmentBooked = () => {
  const navigate = useNavigate();
  return (
    <div>
      <h1 className="appointment-confirmed-text">
        Your appointment has been booked successfully.
      </h1>
      <button onClick={() => navigate("..")}>Back to home</button>
    </div>
  );
};
