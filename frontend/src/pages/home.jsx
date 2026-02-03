import { useNavigate } from "react-router-dom";

export default function Home() {
  const navigate = useNavigate();

  return (
    <div style={styles.page}>
      <div style={styles.navbar}>
        <div style={styles.logo}>FITNESS TRACKER</div>

        <div style={styles.menu}>
          <span>Goals</span>
          <span>Activity</span>
          <span>AI Recommendation</span>
        </div>

        <div style={styles.actions}>
          <span style={styles.login} onClick={() => navigate("/login")}>
            LOG IN
          </span>
          <button style={styles.signupBtn} onClick={() => navigate("/signup")}>
            SIGN UP
          </button>
        </div>
      </div>

      {/* HERO SECTION */}
      <div style={styles.hero}>
        <div style={styles.overlay}></div>

        <div style={styles.heroContent}>
          <div style={styles.heroBox}>
            <h1 style={styles.title}>REACH YOUR BEST</h1>

            <p style={styles.text}>
              Track workouts, monitor progress, join challenges and stay
              motivated with your personal fitness companion.
            </p>

            <button style={styles.cta} onClick={() => navigate("/signup")}>
              SIGN UP
            </button>

            <p style={styles.smallText}>
              Already a member?{" "}
              <span style={styles.link} onClick={() => navigate("/login")}>
                Log In
              </span>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}

/* ================= STYLES ================= */

const styles = {
  page: {
    width: "100%",
    height: "100%",
    fontFamily: "Arial, sans-serif",
  },

  /* NAVBAR */
  navbar: {
    height: "60px",
    background: "#fff",
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between",
    padding: "0 40px",
    position: "fixed",
    top: 0,
    left: 0,
    right: 0,
    zIndex: 10,
    boxShadow: "0 2px 10px rgba(0,0,0,0.1)",
  },

  logo: {
    fontWeight: "900",
    letterSpacing: "1px",
  },

  menu: {
    display: "flex",
    gap: "30px",
    fontWeight: "500",
    cursor: "pointer",
  },

  actions: {
    display: "flex",
    alignItems: "center",
    gap: "20px",
  },

  login: {
    cursor: "pointer",
    fontWeight: "600",
  },

  signupBtn: {
    padding: "10px 18px",
    background: "#000",
    color: "#fff",
    border: "none",
    cursor: "pointer",
    fontWeight: "600",
  },

  /* HERO */
  hero: {
    height: "100vh",
    width: "100vw",
    backgroundImage:
      "url('/src/assets/WhatsApp Image 2026-01-19 at 9.59.11 PM.jpeg')",
    backgroundSize: "105%",           // slight zoom-out
    backgroundPosition: "center left",
    position: "relative",
    display: "flex",
    alignItems: "center",
    paddingTop: "60px",               // navbar height
  },

  /* DARK + GRADIENT OVERLAY */
  overlay: {
    position: "absolute",
    inset: 0,
    background: `
      linear-gradient(
        to right,
        rgba(0,0,0,0.75),
        rgba(0,0,0,0.45),
        rgba(0,0,0,0.75)
      )
    `,
  },

  heroContent: {
    position: "relative",
    zIndex: 2,
    width: "100%",
    display: "flex",
    justifyContent: "flex-end",
    paddingRight: "100px",
  },

  /* GLASS BOX */
  heroBox: {
    background: "rgba(0,0,0,0.6)",
    padding: "40px 45px",
    borderRadius: "14px",
    maxWidth: "520px",
    color: "#fff",

    backdropFilter: "blur(6px)",
    WebkitBackdropFilter: "blur(6px)",

    boxShadow: "0 20px 50px rgba(0,0,0,0.4)",
    border: "1px solid rgba(255,255,255,0.15)",
  },

  title: {
    fontSize: "56px",
    fontWeight: "900",
    lineHeight: "1.1",
    marginBottom: "20px",
  },

  text: {
    fontSize: "16px",
    lineHeight: "1.6",
    marginBottom: "30px",
    maxWidth: "460px",
    opacity: 0.95,
  },

  cta: {
    padding: "14px 40px",
    fontSize: "16px",
    background: "#fff",
    color: "#000",
    border: "none",
    cursor: "pointer",
    fontWeight: "700",
  },

  smallText: {
    marginTop: "18px",
    fontSize: "14px",
    opacity: 0.9,
  },

  link: {
    textDecoration: "underline",
    cursor: "pointer",
    fontWeight: "600",
  },
};
