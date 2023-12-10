import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { SiteLayout } from "./layouts/SiteLayout";
import { Login } from "./pages/Login";
import { Register } from "./pages/Register";
import { ClientLayout } from "./layouts/DashboardLayout";
import { WelcomePage } from "./pages/WelcomePage";
import { BookAppointment } from "./pages/BookAppointment/BookAppointment";
import { AppointmentProvider } from "./pages/BookAppointment/AppointmentProvider";
import { AppointmentBooked } from "./pages/AppointmentBooked";
import AuthProvider from "./Context/AuthProvider";
import ClientMenuPage2 from "./pages/ClientMenuPage2";
import ClientMenuPage3 from "./pages/ClientMenuPage3";
import ExtraMenuPage1 from "./pages/ExtraMenuPage1";
import ExtraMenuPage2 from "./pages/ExtraMenuPage2";
import RequireAuth from "./layouts/RequireAuth";
import RequireLogin from "./layouts/RequireLogin";
import Unauthorized from "./pages/unauthorized";
import StartingPage from "./pages/StartingPage";

export default function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          <Route path="/" element={<SiteLayout />}>
            <Route index element={<StartingPage />}></Route>
            <Route path="login" element={<Login />} />
            <Route path="register" element={<Register />} />
            <Route element={<RequireLogin />}>
              <Route path="dashboard" element={<ClientLayout />}>
                <Route index element={<WelcomePage />} />
                <Route element={<RequireAuth allowedRoles={["ROLE_CLIENT"]} />}>
                  <Route path="appointments" element={<BookAppointment />} />
                  <Route
                    path="appointment-booked"
                    element={<AppointmentBooked />}
                  />
                  <Route
                    path="client-menu-page-2"
                    element={<ClientMenuPage2 />}
                  />
                  <Route
                    path="client-menu-page-3"
                    element={<ClientMenuPage3 />}
                  />
                </Route>
                <Route element={<RequireAuth allowedRoles={["ROLE_EXTRA"]} />}>
                  <Route
                    path="extra-menu-page-1"
                    element={<ExtraMenuPage1 />}
                  />
                  <Route
                    path="extra-menu-page-2"
                    element={<ExtraMenuPage2 />}
                  />
                </Route>
                <Route path="unauthorized" element={<Unauthorized />} />
              </Route>
            </Route>
          </Route>
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  );
}

ReactDOM.createRoot(document.getElementById("root")).render(
  // <React.StrictMode>
  <App />
  // </React.StrictMode>
);
