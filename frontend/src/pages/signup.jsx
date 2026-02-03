import { useState } from "react";
import api from "../api/axios";
import { useNavigate } from "react-router-dom";


export default function Signup() {
  const navigate = useNavigate();
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
    age: "",
    gender: "",
    heightCm: "",
    weightKg: "",
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleRegister = async () => {
  try {
    await api.post("/auth/register", form);
    alert("Registration successful! Please log in.");
    navigate("/login");
  } catch (error) {
    alert("Registration failed. Please try again.");
  }
};

  return (
    <div style={styles.page}>
      <div style={styles.card}>
        <h2 style={styles.title}>Fitness Tracker</h2>
        <p style={styles.subtitle}>Create your account</p>

        <input style={styles.input} name="name" placeholder="Name" onChange={handleChange} />
        <input style={styles.input} name="email" placeholder="Email" onChange={handleChange} />
        <input style={styles.input} name="password" type="password" placeholder="Password" onChange={handleChange} />
        <input style={styles.input} name="age" type="number" placeholder="Age" onChange={handleChange} />

        <select style={styles.input} name="gender" onChange={handleChange}>
          <option value="">Select Gender</option>
          <option value="Male">Male</option>
          <option value="Female">Female</option>
        </select>

        <input style={styles.input} name="heightCm" type="number" placeholder="Height (cm)" onChange={handleChange} />
        <input style={styles.input} name="weightKg" type="number" placeholder="Weight (kg)" onChange={handleChange} />

       <button
  type="button"
  style={styles.button}
  onClick={handleRegister}
>
  Register
</button>

      </div>
    </div>
  );
}

const styles = {
  page: {
   height: "100vh",
    width: "100vw",
    backgroundImage:
      "url('src/assets/WhatsApp Image 2026-01-20 at 2.20.04 PM.jpeg')",
    backgroundSize: "cover",
    backgroundPosition: "center",
    position: "relative",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
  },
  card: {
   // background: "#ffffff",
    padding: "30px",
    width: "340px",
    borderRadius: "10px",
    boxShadow: "0 10px 30px rgba(0,0,0,0.15)",
    display: "flex",
    flexDirection: "column",
  },
  title: {
    textAlign: "center",
    marginBottom: "5px",
    color: "#1a1f24",
    fontSize: "24px",
    fontWeight: "bold",
    
  },
  subtitle: {
    textAlign: "center",
    marginBottom: "20px",
    color: "#253031",
    fontSize: "14px",
  },
  input: {
    padding: "10px",
    marginBottom: "12px",
    borderRadius: "5px",
    border: "1px solid #ccc",
    fontSize: "14px",
  },
  button: {
    marginTop: "10px",
    padding: "10px",
    borderRadius: "5px",
    border: "none",
    background: "#27ae60",
    color: "white",
    fontSize: "15px",
    fontWeight: "bold",
    cursor: "pointer",
  },
};
