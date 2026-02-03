import { useEffect, useState } from "react";
import api from "../api/axios";
import "../styles/Activities.css";

const ACTIVITY_MAP = {
  Walking: 1,
  Running: 2,
  Cycling: 3,
  Yoga: 4,
  "Gym Workout": 5,
  Swimming: 6,
};

export default function ActivityModal({
  onClose,
  onSuccess,
  mode = "add",
  activityData
}) {
  const [activityName, setActivityName] = useState("");
  const [stepsCount, setStepsCount] = useState("");
  const [durationMinutes, setDurationMinutes] = useState("");
  const [weight, setWeight] = useState("");
  const [activityDate, setActivityDate] = useState("");

  const showSteps =
    activityName === "Walking" || activityName === "Running";

  useEffect(() => {
    if (mode === "edit" && activityData) {
      setActivityName(activityData.activityName);
      setStepsCount(activityData.stepsCount || "");
      setDurationMinutes(activityData.durationMinutes || "");
      setWeight(activityData.weightAtThatTime || "");
      setActivityDate(activityData.activityDate);
    }
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const activityId = ACTIVITY_MAP[activityName];
    if (!activityId || !weight) {
      alert("Activity and weight are required");
      return;
    }

    const payload = {
      activityId,
      stepsCount: showSteps ? Number(stepsCount) : null,
      durationMinutes: durationMinutes ? Number(durationMinutes) : null,
      weight: Number(weight),
      activityDate
    };

    try {
      if (mode === "edit") {
        await api.put(`activities/${activityData.id}`, payload);
      } else {
        await api.post("activities", payload);
      }
      onSuccess();
      onClose();
    } catch {
      alert("Failed to save activity");
    }
  };

  return (
    <div className="modal-overlay">
      <div className="modal-card improved">

        <div className="modal-header">
          <span>➕</span>
          <h3>{mode === "edit" ? "Edit Activity" : "Add Activity"}</h3>
        </div>

        <form onSubmit={handleSubmit} className="activity-form-vertical">

          <div className="form-group">
            <label>Activity</label>
            <select
              value={activityName}
              onChange={e => setActivityName(e.target.value)}
              required
            >
              <option value="">Select activity</option>
              {Object.keys(ACTIVITY_MAP).map(a => (
                <option key={a} value={a}>{a}</option>
              ))}
            </select>
          </div>

          {showSteps && (
            <div className="form-group">
              <label>Steps</label>
              <input
                type="number"
                value={stepsCount}
                onChange={e => setStepsCount(e.target.value)}
              />
            </div>
          )}

          <div className="form-group">
            <label>Duration (minutes)</label>
            <input
              type="number"
              value={durationMinutes}
              onChange={e => setDurationMinutes(e.target.value)}
            />
          </div>

          <div className="form-group weight-highlight">
            <label>Current Weight (kg)</label>
            <input
              type="number"
              value={weight}
              onChange={e => setWeight(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label>Date</label>
            <input
              type="date"
              value={activityDate}
              onChange={e => setActivityDate(e.target.value)}
              required
            />
          </div>

          <div className="modal-actions spaced">
            <button type="button" className="cancel-btn" onClick={onClose}>
              Cancel
            </button>
            <button type="submit" className="save-btn">
              Save
            </button>
          </div>

        </form>
      </div>
    </div>
  );
}
