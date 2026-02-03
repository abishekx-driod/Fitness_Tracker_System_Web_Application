import axios from "axios";

const BASE_URL = "http://localhost:8080/api/ai/recommendation";

export const fetchAiRecommendation = async (goal, level) => {
  const token = localStorage.getItem("token");

  if (!token) {
    throw new Error("NO_TOKEN");
  }

  try {
    const response = await axios.post(
      BASE_URL,
      { goal, level },
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        timeout: 120000,
      }
    );
    return response;
  } catch (error) {
    if (error.response?.status === 401) {
      throw new Error("TOKEN_EXPIRED");
    }
    throw error;
  }
};
