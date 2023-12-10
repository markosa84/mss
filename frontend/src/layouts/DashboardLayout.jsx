import { Outlet, NavLink } from "react-router-dom";
import { useAuth } from "../Context/useAuth";

const ROLES_FUNCTIONALITIES = {
  ROLE_CLIENT: [
    { name: "Book an appointment", url: "appointments" },
    { name: "2nd client functionality", url: "client-menu-page-2" },
    { name: "3rd client functionality", url: "client-menu-page-3" },
  ],
  ROLE_EXTRA: [
    { name: "1st extra functionality", url: "extra-menu-page-1" },
    { name: "2nd extra functionality", url: "extra-menu-page-2" },
  ],
};

export const ClientLayout = () => {
  const { auth } = useAuth();

  function getMenuItems() {
    return auth.roles.map((role) =>
      ROLES_FUNCTIONALITIES[role].map((_role, i) => (
        <li key={i}>
          <NavLink to={_role.url}>{_role.name}</NavLink>
        </li>
      ))
    );
  }
  return (
    <div className="container">
      <div className="layout-flex">
        <div className="user-sidebar">
          <nav>
            <ul>
              {getMenuItems()}
              <li>
                <NavLink to="extra-menu-page-1">extra1 baked in</NavLink>
              </li>
            </ul>
          </nav>
        </div>
        <div className="user-content">
          <h3 style={{ background: "aquamarine", padding: "1rem" }}>
            {auth.name}
          </h3>
          <Outlet />
        </div>
      </div>
    </div>
  );
};
