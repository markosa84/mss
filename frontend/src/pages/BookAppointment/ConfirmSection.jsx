import { format, parse } from "date-fns";
import { useNavigate } from "react-router-dom";
import { SectionHeader } from "./SectionHeader";
import { useAppointmentSelector } from "./useAppointmentSelector";
import { axios1 } from "../../api/axios";
import { useAuth } from "../../Context/useAuth";

export function ConfirmSection({ appointment }) {
  const { auth } = useAuth();
  const { departmentDoctors } = useAppointmentSelector();
  const navigate = useNavigate();
  console.log("appointment to save: ", appointment);
  đ;
  async function confirm() {
    try {
      await axios1.post("/appointment/save", appointment, {
        headers: { Authorization: auth.accessToken },
      });
      navigate("../appointment-booked");
    } catch (error) {
      console.log(error);
    }
    console.log("Appointment sent in", appointment);
  }
  return (
    <div>
      <SectionHeader>4. Please confirm your appointment</SectionHeader>
      <h4 className="appointment-summary">{`Your appointment is with ${
        departmentDoctors.find((doc) => doc.doctorId === appointment.doctorID)
          .name
      }, on ${format(
        parse(appointment.date, "yyyy-MM-dd", new Date()),
        "cccc, do LLLL yyyy"
      )} at ${appointment.startTime}.`}</h4>
      <button onClick={confirm} className="confirm-appointment-button">
        Confirm appointment
      </button>
    </div>
  );
}
