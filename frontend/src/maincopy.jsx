import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { SiteLayout } from "./layouts/SiteLayout";
import { Login } from "./pages/Login";
import { Register } from "./pages/Register";
import { ClientLayout } from "./layouts/ClientLayout";
import { WelcomePage } from "./pages/WelcomePage";
import { BookAppointment } from "./pages/BookAppointment/BookAppointment";
import { AppointmentProvider } from "./pages/BookAppointment/AppointmentProvider";
import { AppointmentBooked } from "./pages/AppointmentBooked";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<SiteLayout />}>
          <Route path="login" element={<Login />} />
          <Route path="register" element={<Register />} />
          <Route path="client" element={<ClientLayout />}>
            <Route index element={<WelcomePage />} />
            <Route
              path="appointments"
              element={
                <AppointmentProvider>
                  <BookAppointment />
                </AppointmentProvider>
              }
            />
            <Route path="appointment-booked" element={<AppointmentBooked />} />
          </Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

ReactDOM.createRoot(document.getElementById("root")).render(
  // <React.StrictMode>
  <App />
  // </React.StrictMode>
);
