import { useState } from "react";
import ReactMarkdown from "react-markdown";
import { fetchAiRecommendation } from "../api/aiRecommendation";
import "../styles/AIRecommendation.css";

function AiRecommendation() {
  const [goal, setGoal] = useState("Weight Loss");
  const [level, setLevel] = useState("Beginner");
  const [result, setResult] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleGenerate = async () => {
    setLoading(true);
    setError("");
    setResult("");

    try {
      const response = await fetchAiRecommendation(goal, level);
      setResult(response.data);
    } catch (err) {
      setError("AI is taking too long or failed. Try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="ai-card">
      <h2>🤖 AI Fitness Recommendation</h2>

      <div className="ai-controls">
        <select value={goal} onChange={(e) => setGoal(e.target.value)}>
          <option>Weight Loss</option>
          <option>Muscle Gain</option>
          <option>Endurance</option>
          <option>General Fitness</option>
        </select>

        <select value={level} onChange={(e) => setLevel(e.target.value)}>
          <option>Beginner</option>
          <option>Intermediate</option>
          <option>Advanced</option>
        </select>

        <button onClick={handleGenerate} disabled={loading}>
          {loading ? "Generating..." : "Generate Plan"}
        </button>
      </div>

      {loading && <p className="ai-loading">⏳ AI is thinking...</p>}

      {error && <p className="ai-error">{error}</p>}

      {result && (
        <div className="ai-result">
          <ReactMarkdown>{result}</ReactMarkdown>
        </div>
      )}
    </div>
  );
}

export default AiRecommendation;
