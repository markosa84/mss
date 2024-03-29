import { createBrowserRouter } from "react-router-dom";
import { SiteLayout } from "./layouts/SiteLayout";
import { Login } from "./pages/Login";
import { Register } from "./pages/Register";
import { ClientLayout } from "./layouts/ClientLayout";
import { WelcomePage } from "./pages/WelcomePage";
import { BookAppointment } from "./pages/BookAppointment/BookAppointment";
import { AppointmentBooked } from "./pages/AppointmentBooked";
import { AppointmentProvider } from "./pages/BookAppointment/AppointmentProvider";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <SiteLayout />,
    children: [
      { path: "login", element: <Login /> },
      { path: "register", element: <Register /> },
      {
        path: "client",
        element: <ClientLayout />,
        children: [
          { index: true, element: <WelcomePage /> },
          {
            path: "appointments",
            element: (
              <AppointmentProvider>
                <BookAppointment />
              </AppointmentProvider>
            ),
          },
          { path: "appointment-booked", element: <AppointmentBooked /> },
        ],
      },
    ],
  },
]);
