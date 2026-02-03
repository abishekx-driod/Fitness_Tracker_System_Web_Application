import { useState } from "react";

export default function Bmi() {
  const [h, setH] = useState("");
  const [w, setW] = useState("");
  const [bmi, setBmi] = useState(null);

  const calculate = () => {
    const heightM = h / 100;
    setBmi((w / (heightM * heightM)).toFixed(2));
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>BMI Calculator</h2>

      <input placeholder="Height (cm)" onChange={e => setH(e.target.value)} style={style} />
      <input placeholder="Weight (kg)" onChange={e => setW(e.target.value)} style={style} />

      <button onClick={calculate}>Calculate</button>

      {bmi && <h3>Your BMI: {bmi}</h3>}
    </div>
  );
}

const style = { display: "block", marginBottom: 10, padding: 8 };
