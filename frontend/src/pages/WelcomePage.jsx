import { useAuth } from "../Context/useAuth";

export const WelcomePage = () => {
  const { auth } = useAuth();
  return (
    <div>
      Welcome page comes here
      <br />
      Roles are:
      {auth.roles?.map((role, i) => (
        <h4 key={i}>{role}</h4>
      ))}
    </div>
  );
};
