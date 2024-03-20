import React, { useEffect, useState } from "react";
import { useLocation, useNavigate, useSearchParams } from "react-router-dom";
import { axios1 } from "../api/axios";

function Activate() {
  const [success, setSuccess] = useState(false);
  const navigate = useNavigate();
  const [sp, setSp] = useSearchParams();
  const activationKey = sp.get("code");
  console.log(activationKey);
  useEffect(() => {
    axios1.post("/register/verify", { code: activationKey }).then((res) => {
      console.log(res);
      setSuccess(true);
    });
  }, []);

  return (
    <div>
      <h2>Thank you for activating your account.</h2>
      {success && (
        <div>
          <p>
            You have now successfully activated your account. Please log in to
            proceed.
          </p>
          <br />
          <button onClick={() => navigate("../login")}>Log in</button>
        </div>
      )}
    </div>
  );
}

export default Activate;
