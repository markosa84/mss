import { useNavigate } from "react-router-dom";

export default function StartingPage() {
  const navigate = useNavigate();

  const style = {
    marginTop: "5rem",
    marginLeft: "1rem",
    padding: "1rem 2rem",
    fontWeight: "bold",
    fontSize: "1.5rem",
    borderRadius: "8px",
  };

  return (
    <div style={{ display: "flex", justifyContent: "center" }}>
      <button style={style} onClick={() => navigate("/login")}>
        Log in
      </button>
      <button style={style} onClick={() => navigate("/register")}>
        Register
      </button>
    </div>
  );
}
