import { useNavigate } from "react-router-dom";

export default function StartingPage() {
  const navigate = useNavigate();

  return (
    <div style={{ display: "flex", justifyContent: "center" }}>
      <button
        style={{
          marginTop: "5rem",
          padding: "1rem 2rem",
          fontWeight: "bold",
          fontSize: "1.5rem",
          borderRadius: "8px",
        }}
        onClick={() => navigate("/login")}
      >
        Log in
      </button>
    </div>
  );
}
