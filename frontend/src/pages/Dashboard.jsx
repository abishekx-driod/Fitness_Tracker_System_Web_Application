import { useEffect, useState } from "react";
import api from "../api/axios";
import "../styles/Dashboard.css";

export default function Dashboard() {
  const [activities, setActivities] = useState([]);

  useEffect(() => {
    loadDashboard();
  }, []);

  const loadDashboard = async () => {
    const res = await api.get("/activities");
    setActivities(res.data || []);
  };

  const today = new Date().toISOString().split("T")[0];

  const todayCalories = activities
    .filter(a => a.activityDate === today)
    .reduce((s, a) => s + (a.caloriesBurned || 0), 0);

  const recentActivities = activities.slice(0, 4);

  return (
  <div className="dash-bg">

    {/* HERO */}
    <div className="dash-hero compact">
      <h1>👋 Welcome Back</h1>
      <p>Today’s fitness overview</p>
    </div>

    {/* STATS GRID */}
    <div className="dash-stats-grid">

      <div className="stat-card gradient-orange">
        <div className="stat-icon">🔥</div>
        <div>
          <span>Today Calories</span>
          <h2>{Math.round(todayCalories)} kcal</h2>
        </div>
      </div>

      <div className="stat-card gradient-blue">
        <div className="stat-icon">🏃</div>
        <div>
          <span>Total Activities</span>
          <h2>{activities.length}</h2>
        </div>
      </div>

      {/* Future ready */}
      <div className="stat-card gradient-green">
        <div className="stat-icon">🎯</div>
        <div>
          <span>Goals</span>
          <h2>On Track</h2>
        </div>
      </div>

    </div>

    {/* RECENT ACTIVITIES */}
    <div className="dash-section">
      <h3>Recent Activities</h3>

      <div className="activity-list">
        {recentActivities.map(a => (
          <div className="activity-item" key={a.id}>
            <div>
              <strong>{a.activityName}</strong>
              <p>{a.activityDate}</p>
            </div>
            <span className="kcal-chip">
              🔥 {Math.round(a.caloriesBurned)} kcal
            </span>
          </div>
        ))}
      </div>
    </div>

  </div>
);
}
