import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/home";
import Login from "./pages/login";
import Signup from "./pages/signup";
import Dashboard from "./pages/Dashboard";
import Profile from "./pages/profile";
import WorkoutHistory from "./pages/workouthistory";
import Bmi from "./pages/Bmi";
import Settings from "./pages/Settings";
import GoalsPage from "./pages/GoalsPage";
import ActivitiesPage from "./pages/ActivitiesPage";
import AiRecommendationPage from "./pages/AiRecommendationPage";

import ProtectedLayout from "./layout/ProtectedLayout";



export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route element={<ProtectedLayout />}>
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/goals" element={<GoalsPage />} />
           <Route path="/activity" element={<ActivitiesPage />} />
            <Route path="/ai" element={<AiRecommendationPage />}  />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}
