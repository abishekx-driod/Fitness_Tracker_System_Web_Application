import { createContext, useContext, useState } from "react";
import api from "../api/axios";

const DashboardContext = createContext();

export function DashboardProvider({ children }) {
  const [dashboard, setDashboard] = useState(null);
  const [loading, setLoading] = useState(false);

  const fetchDashboard = async () => {
    try {
      setLoading(true);
      const token = localStorage.getItem("token");

      const res = await api.get("/dashboard", {
        headers: { Authorization: `Bearer ${token}` },
      });

      setDashboard(res.data);
    } catch (err) {
      console.error("Dashboard fetch failed", err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <DashboardContext.Provider value={{ dashboard, loading, fetchDashboard }}>
      {children}
    </DashboardContext.Provider>
  );
}

export const useDashboard = () => useContext(DashboardContext);
