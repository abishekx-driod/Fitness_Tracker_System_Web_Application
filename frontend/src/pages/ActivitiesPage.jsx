import { useEffect, useState } from "react";
import api from "../api/axios";
import ActivityModal from "../component/ActivityModal";
import "../styles/Activities.css";

export default function ActivitiesPage() {
  const [activities, setActivities] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showModal, setShowModal] = useState(false);
  const [editingActivity, setEditingActivity] = useState(null);

  useEffect(() => {
    loadActivities();
  }, []);

  const loadActivities = async () => {
    setLoading(true);
    try {
      const res = await api.get("activities");
      setActivities(res.data);
    } catch (err) {
      console.error("Failed to load activities", err);
    } finally {
      setLoading(false);
    }
  };

  const deleteActivity = async (id) => {
    if (!window.confirm("Delete this activity?")) return;
    try {
      await api.delete(`activities/${id}`);
      loadActivities();
    } catch (err) {
      console.error("Delete failed", err);
    }
  };

  const getDateBadge = (dateStr) => {
    const today = new Date();
    const date = new Date(dateStr);

    today.setHours(0,0,0,0);
    date.setHours(0,0,0,0);

    const diff =
      (today.getTime() - date.getTime()) /
      (1000 * 60 * 60 * 24);

    if (diff === 0) return "today";
    if (diff === 1) return "yesterday";
    return null;
  };

  return (
    <div className="activities-page">

      <div className="activities-header">
        <h2>🏃 Activities</h2>
        <button onClick={() => setShowModal(true)}>+ Add Activity</button>
      </div>

      {loading ? (
        <div className="skeleton-list">
          {[1,2,3].map(i => (
            <div className="skeleton-card" key={i}></div>
          ))}
        </div>
      ) : activities.length === 0 ? (
        <p className="empty-text">No activities recorded</p>
      ) : (
        <div className="activities-list">
          {activities.map((a) => {
            const badge = getDateBadge(a.activityDate);

            return (
              <div className="activity-card" key={a.id}>
                <div className="activity-left">

                  <div className="activity-title">
                    <span className="activity-icon">🏃</span>
                    <h3>{a.activityName}</h3>
                  </div>

                  <p className={`activity-date ${badge}`}>
                    📅 {badge === "today"
                      ? "Today"
                      : badge === "yesterday"
                      ? "Yesterday"
                      : a.activityDate}
                  </p>

                  <div className="activity-metrics">
                    {a.stepsCount && (
                      <span className="metric">👣 {a.stepsCount} steps</span>
                    )}
                    {a.durationMinutes && (
                      <span className="metric">⏱ {a.durationMinutes} mins</span>
                    )}
                    {a.caloriesBurned && (
                      <span className="metric calories">
                        🔥 {Math.round(a.caloriesBurned)} kcal
                      </span>
                    )}
                  </div>
                </div>

                <div className="activity-actions">
                  <button
                    className="edit-btn"
                    onClick={() => setEditingActivity(a)}
                  >
                    ✏️
                  </button>
                  <button
                    className="delete-btn"
                    onClick={() => deleteActivity(a.id)}
                  >
                    🗑
                  </button>
                </div>
              </div>
            );
          })}
        </div>
      )}

      {showModal && (
        <ActivityModal
          mode="add"
          onClose={() => setShowModal(false)}
          onSuccess={loadActivities}
        />
      )}

      {editingActivity && (
        <ActivityModal
          mode="edit"
          activityData={editingActivity}
          onClose={() => setEditingActivity(null)}
          onSuccess={loadActivities}
        />
      )}
    </div>
  );
}
