import { NavLink, useNavigate } from "react-router-dom";
import "./Navbar.css";

export default function Navbar() {
  const navigate = useNavigate();

  const logout = () => {
    localStorage.clear();
    navigate("/login");
  };

  return (
    <nav className="navbar">
      {/* LEFT */}
      <div className="nav-left">
        <span className="logo">FITNESS TRACKER</span>
      </div>

      {/* CENTER */}
      <div className="nav-center">
        <NavLink to="/dashboard" className="nav-link">
          Dashboard
        </NavLink>
        <NavLink to="/goals" className="nav-link">
          Goals
        </NavLink>
        <NavLink to="/activity" className="nav-link">
          Activity
        </NavLink>
        <NavLink to="/ai" className="nav-link">
          AI Recommendation
        </NavLink>
      </div>

      {/* RIGHT */}
      <div className="nav-right">
        <NavLink to="/profile" className="nav-link">
          Profile
        </NavLink>
        <button className="logout-btn" onClick={logout}>
          Logout
        </button>
      </div>
    </nav>
  );
}
