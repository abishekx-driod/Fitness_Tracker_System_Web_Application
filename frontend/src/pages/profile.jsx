export default function Profile() {
  return (
    <div style={{ padding: 20 }}>
      <h2>My Profile</h2>

      <input placeholder="Name" style={style} />
      <input placeholder="Age" style={style} />
      <input placeholder="Height (cm)" style={style} />
      <input placeholder="Weight (kg)" style={style} />
      <input placeholder="Goal" style={style} />

      <button>Save Profile</button>
    </div>
  );
}

const style = { display: "block", marginBottom: 10, padding: 8 };
