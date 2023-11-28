import { Navigate, Outlet, useLocation } from "react-router-dom";
import { useAuth } from "../Context/useAuth";

export default function RequireAuth({ allowedRoles }) {
  const { auth } = useAuth();
  const location = useLocation();

  return auth.roles?.find((role) => allowedRoles.includes(role)) ? (
    <Outlet />
  ) : (
    <Navigate to={"unauthorized"} state={{ from: location }} replace />
  );
}
