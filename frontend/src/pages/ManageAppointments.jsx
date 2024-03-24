import { af } from "date-fns/locale";
import { useEffect, useState } from "react";
import { axios1 } from "../api/axios";
import { useAuth } from "../Context/useAuth";
import {
  format,
  isAfter,
  parseISO,
  parseJSON,
  startOfTomorrow,
} from "date-fns";

export default function ManageAppointments() {
  const { auth } = useAuth();
  const [appointments, setAppointments] = useState([]);
  console.log(appointments);

  const fetchData = async () => {
    const res = await axios1.get(`/appointment/get/client/${auth.userId}`, {
      headers: { Authorization: auth.accessToken },
    });
    setAppointments(res.data);
  };

  useEffect(() => {
    fetchData();
  }, []);

  function cancelAppointment(appiontmentId) {
    axios1
      .delete(`/appointment/delete/byClient?id=${appiontmentId}`, {
        headers: { Authorization: auth.accessToken },
      })
      .then((res) => fetchData());
  }

  return (
    <div>
      <h1>My appointments:</h1>

      <table className="appointment">
        <thead>
          <tr>
            <th scope="col">Date</th>
            <th scope="col">Time</th>
            <th scope="col">Department</th>
            <th scope="col">Doctor</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {appointments.map((a) => {
            const startDate = parseISO(a.startDate);
            const endDate = parseISO(a.endDate);

            return (
              <tr key={a.appointmentID}>
                <td>{format(startDate, "EEEE, do MMMM yyyy")}</td>
                <td>
                  {format(startDate, "HH:mm")} - {format(endDate, "HH:mm")}
                </td>
                <td>{a.areaOfExpertise}</td>
                <td>Doctor with the id: {a.drID}</td>
                {isAfter(startDate, startOfTomorrow(new Date())) && (
                  <td>
                    <button onClick={() => cancelAppointment(a.appointmentID)}>
                      Cancel appointment
                    </button>
                  </td>
                )}
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
}
