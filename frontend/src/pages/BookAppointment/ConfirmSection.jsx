import { format } from "date-fns";
import { useNavigate } from "react-router-dom";

export function ConfirmSection({ appointment }) {
  const navigate = useNavigate();

  function confirm() {
    console.log("Appointment confirmed", appointment);
    navigate("../appointment-booked");
  }
  return (
    <div>
      <h3 className="booking-instruction">
        4. Please confirm your appointment
      </h3>
      <h4 className="appointment-summary">{`Your appointment is with ${
        appointment.doctorName
      }, on ${format(
        appointment.appointmentDateTime,
        "EEEE, do MMMM, yyyy"
      )} at ${format(appointment.appointmentDateTime, "HH:mm")}.`}</h4>
      <button onClick={confirm} className="confirm-appointment-button">
        Confirm appointment
      </button>
    </div>
  );
}
