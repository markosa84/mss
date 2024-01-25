import React, { useEffect } from "react";
import { useLocation, useSearchParams } from "react-router-dom";
import { axios1 } from "../api/axios";

function Activate() {
  const [sp, setSp] = useSearchParams();
  const activationKey = sp.get("code");
  console.log(activationKey);
  useEffect(() => {
    axios1.post("/");
  }, []);

  return <div>Activate</div>;
}

export default Activate;
