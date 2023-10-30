import { Outlet } from "react-router-dom";
import { Header } from "../components/Header";
import { Footer } from "../components/footer";
export const SiteLayout = () => {
  return (
    <>
      <Header />
      <Outlet />
      <Footer />
    </>
  );
};
