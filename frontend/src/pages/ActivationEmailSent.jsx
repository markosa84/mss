import { af } from "date-fns/locale";
import { useNavigate } from "react-router-dom";

export const ActivationEmailSent = () => {
  const navigate = useNavigate();
  return (
    <div>
      <h1
        style={{
          marginTop: "3rem",
          textAlign: "center",
          opacity: ".5",
        }}
      >
        An activation email has been sent to the email address you provided.
        Please click the on the activation link to activate your account.
      </h1>
    </div>
  );
};
