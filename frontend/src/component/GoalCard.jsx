export default function GoalCard({ goal, current, onEdit, onDelete }) {
  const titles = {
    steps: "🚶 Steps",
    calories: "🔥 Calories",
    workout: "🏋️ Workout",
    water: "💧 Water"
  };

  const percent =
    current == null
      ? 0
      : Math.min(Math.round((current / goal.targetValue) * 100), 100);

  return (
    <div className="goal-card">
      <h4>{titles[goal.goalType]}</h4>

      {current == null ? (
        <p className="inactive-text">Not tracked yet</p>
      ) : (
        <>
          <p>{current} / {goal.targetValue}</p>
          <div className="progress-bar">
            <div
              className="progress-fill"
              style={{ width: `${percent}%` }}
            />
          </div>
          <span>{percent}%</span>
        </>
      )}

      <div className="goal-actions">
        <button onClick={() => onEdit(goal)}>✏️ Edit</button>
        <button className="delete-btn" onClick={() => onDelete(goal.goalId)}>
          🗑 Delete
        </button>
      </div>
    </div>
  );
}
