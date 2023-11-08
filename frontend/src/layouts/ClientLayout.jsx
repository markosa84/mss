import { Outlet, NavLink } from "react-router-dom";

export const ClientLayout = () => {
  return (
    <div className="container">
      <div className="layout-flex">
        <div className="user-sidebar">
          <nav>
            <ul>
              <li>
                <NavLink to="appointments">Book an appointment</NavLink>
              </li>
              <li>
                <NavLink>Menu 2</NavLink>
              </li>
              <li>
                <NavLink>Menu 3</NavLink>
              </li>
            </ul>
          </nav>
        </div>
        <div className="user-content">
          <h3 style={{ background: "aquamarine", padding: "1rem" }}>
            Júz Erzsébet
          </h3>
          <Outlet />
        </div>
      </div>
    </div>
  );
};
