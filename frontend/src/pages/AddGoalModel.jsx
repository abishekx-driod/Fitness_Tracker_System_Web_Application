import { useState } from "react";
import api from "../api/api";

export default function AddGoalModal({ onClose, onSuccess }) {
  const [form, setForm] = useState({
    goalType: "steps",
    targetValue: "",
    startDate: "",
    endDate: ""
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    await api.post("/goals", {
      goalType: form.goalType,
      targetValue: Number(form.targetValue),
      startDate: form.startDate,
      endDate: form.endDate
    });

    onSuccess(); // reload goals
    onClose();   // close modal
  };

  return (
    <div className="modal-overlay">
      <div className="modal-box">
        <h3>➕ Add New Goal</h3>

        <form onSubmit={handleSubmit}>
          <label>Goal Type</label>
          <select
            name="goalType"
            value={form.goalType}
            onChange={handleChange}
          >
            <option value="steps">Steps</option>
            <option value="calories">Calories</option>
            <option value="workout">Workout Time</option>
            <option value="water">Water Intake</option>
          </select>

          <label>Target Value</label>
          <input
            type="number"
            name="targetValue"
            placeholder="Enter target"
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
            <button type="button" onClick={onClose} className="cancel-btn">
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
