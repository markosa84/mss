import { Navigate, Outlet, useLocation } from "react-router-dom";
import { useAuth } from "../Context/useAuth";

export default function RequireLogin() {
  const { auth } = useAuth();
  const location = useLocation();

  return auth.username ? (
    <Outlet />
  ) : (
    <Navigate to={"/login"} state={{ from: location }} replace />
  );
}
