import { useEffect, useState } from "react";
import api from "../api/axios";
import GoalFormModal from "../component/GoalFormModal";
import "../styles/Goals.css";

export default function GoalsPage() {
  const [goals, setGoals] = useState([]);
  const [stats, setStats] = useState({});
  const [showModal, setShowModal] = useState(false);
  const [editGoal, setEditGoal] = useState(null);

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    try {
      const goalsRes = await api.get("/goals");
     // const statsRes = await api.get("/dashboard"); // ✅ REQUIRED
      
      setGoals(goalsRes.data);
      //setStats(statsRes.data);
    } catch (err) {
      console.error("Failed to load goals or stats", err);
    }
  };

  const getCurrentValue = (type) => {
    switch (type) {
      case "steps":
        return stats.steps ?? 0;
      case "calories":
        return stats.caloriesBurned ?? 0;
      case "workout":
        return stats.workoutMinutes ?? 0;
      case "water":
        return stats.waterIntake ?? 0;
      default:
        return 0;
    }
  };

  const deleteGoal = async (id) => {
    if (!window.confirm("Delete this goal?")) return;
    await api.delete(`/goals/${id}`);
    loadData();
  };

  const openAdd = () => {
    setEditGoal(null);
    setShowModal(true);
  };

  const openEdit = (goal) => {
    setEditGoal(goal);
    setShowModal(true);
  };

  return (
    <div className="goals-page">
      <div className="goals-header">
        <h2>🎯 My Goals</h2>
        <button className="add-btn" onClick={openAdd}>
          + Add Goal
        </button>
      </div>

      {goals.length === 0 ? (
        <p className="empty-text">No goals created yet</p>
      ) : (
        <div className="goals-grid">
          {goals.map((g) => {
            const current = g.currentValue;
            const isCompleted = current >= g.targetValue;

            const percent = Math.min(
              Math.round((current / g.targetValue) * 100),
              100
            );

            return (
              <div
                className={`goal-card ${isCompleted ? "completed" : ""}`}
                key={g.goalId}
              >
                <h3>{g.goalType.toUpperCase()}</h3>

                <p className="goal-values">
                  {current} / {g.targetValue}
                </p>

                <div className="progress-bar">
                  <div
                    className={`progress-fill ${
                      isCompleted ? "completed-bar" : ""
                    }`}
                    style={{ width: `${percent}%` }}
                  />
                </div>

                <div className="goal-footer">
                  <span>{percent}%</span>
                  <span
                    className={`status ${
                      isCompleted ? "completed-text" : ""
                    }`}
                  >
                    {isCompleted ? "COMPLETED" : g.status}
                  </span>
                </div>

                <div className="goal-actions">
                  <button
                    onClick={() => openEdit(g)}
                    disabled={isCompleted}
                    className={isCompleted ? "disabled-btn" : ""}
                  >
                    ✏️ Edit
                  </button>

                  <button
                    className="delete-btn"
                    onClick={() => deleteGoal(g.goalId)}
                  >
                    🗑 Delete
                  </button>
                </div>
              </div>
            );
          })}
        </div>
      )}

      {showModal && (
        <GoalFormModal
          editGoal={editGoal}
          onClose={() => setShowModal(false)}
          onSuccess={loadData}
        />
      )}
    </div>
  );
}
