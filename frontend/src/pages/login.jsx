import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axios";

export default function Login() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    email: "",
    password: "",
  });

  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleLogin = async () => {
    if (!form.email || !form.password) {
      alert("Please enter email and password");
      return;
    }

    try {
      setLoading(true);

      const response = await api.post("/auth/login", form);
      localStorage.setItem("token", response.data.token);
      localStorage.setItem("user", JSON.stringify(response.data));

      alert("Login successful ✅");

      navigate("/dashboard");

    } catch (error) {
      console.error(error.response?.data || error.message);
      alert("Login failed. Please check your credentials ❌");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={styles.page}>
      <div style={styles.overlay}></div>

      <div style={styles.card}>
        <h2 style={styles.title}>Fitness Tracker System</h2>
        <p style={styles.subtitle}>Welcome back 👋</p>

        <input
          style={styles.input}
          name="email"
          placeholder="Email"
          onChange={handleChange}
        />

        <input
          style={styles.input}
          type="password"
          name="password"
          placeholder="Password"
          onChange={handleChange}
        />

        {/* ✅ FIXED BUTTON */}
        
        <button
          style={styles.button}
          onClick={handleLogin}
          disabled={loading}
        >
          {loading ? "Logging in..." : "Login"}
        </button>

        <p style={styles.text}>
          New user?{" "}
          <span style={styles.link} onClick={() => navigate("/signup")}>
            Create account
          </span>
        </p>
      </div>
    </div>
  );
}


const styles = {
  page: {
    height: "100vh",
    width: "100vw",
    backgroundImage:
      "url('/src/assets/victor-freitas-WvDYdXDzkhs-unsplash.jpg')",
    backgroundSize: "cover",
    backgroundPosition: "center",
    position: "relative",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
  },

  overlay: {
    position: "absolute",
    inset: 0,
    background: "rgba(0,0,0,0.65)",
  },

  card: {
    position: "relative",
    zIndex: 1,
    background: "rgba(255,255,255,0.96)",
    padding: "40px",
    width: "360px",
    borderRadius: "14px",
    boxShadow: "0 20px 50px rgba(0,0,0,0.4)",
    textAlign: "center",
  },

  title: {
    marginBottom: "6px",
    fontWeight: "700",
  },

  subtitle: {
    marginBottom: "25px",
    color: "#666",
    fontSize: "14px",
  },

  input: {
    width: "100%",
    padding: "12px",
    marginBottom: "15px",
    borderRadius: "6px",
    border: "1px solid #ccc",
    fontSize: "14px",
  },

  button: {
    width: "100%",
    padding: "12px",
    background: "#27ae60",
    border: "none",
    borderRadius: "6px",
    color: "#fff",
    fontSize: "16px",
    fontWeight: "bold",
    cursor: "pointer",
  },

  text: {
    marginTop: "18px",
    fontSize: "14px",
  },

  link: {
    color: "#27ae60",
    cursor: "pointer",
    fontWeight: "600",
  },
};
