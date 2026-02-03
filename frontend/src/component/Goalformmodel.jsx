import { useState } from "react";
import api from "../api/api";

export default function GoalFormModal({ editGoal, onClose, onSuccess }) {
  const [form, setForm] = useState({
    goalType: editGoal?.goalType || "steps",
    targetValue: editGoal?.targetValue || "",
    startDate: editGoal?.startDate || "",
    endDate: editGoal?.endDate || ""
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      goalType: form.goalType,
      targetValue: Number(form.targetValue),
      startDate: form.startDate,
      endDate: form.endDate
    };

    if (editGoal) {
      await api.put(`/goals/${editGoal.goalId}`, payload);
    } else {
      await api.post("/goals", payload);
    }

    onSuccess();
    onClose();
  };

  return (
    <div className="modal-overlay">
      <div className="modal-box">
        <h3>{editGoal ? "✏️ Edit Goal" : "➕ Add Goal"}</h3>

        <form onSubmit={handleSubmit}>
          <label>Goal Type</label>
          <select
            name="goalType"
            value={form.goalType}
            onChange={handleChange}
          >
            <option value="steps">Steps</option>
            <option value="calories">Calories</option>
            <option value="workout">Workout</option>
            <option value="water">Water</option>
          </select>

          <label>Target Value</label>
          <input
            type="number"
            name="targetValue"
            value={form.targetValue}
            onChange={handleChange}
            required
          />

          <label>Start Date</label>
          <input
            type="date"
            name="startDate"
            value={form.startDate}
            onChange={handleChange}
            required
          />

          <label>End Date</label>
          <input
            type="date"
            name="endDate"
            value={form.endDate}
            onChange={handleChange}
            required
          />

          <div className="modal-actions">
            <button type="submit" className="save-btn">Save</button>
            <button type="button" className="cancel-btn" onClick={onClose}>
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
