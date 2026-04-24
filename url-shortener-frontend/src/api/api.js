import axios from "axios";

export default axios.create({
    baseURL: import.meta.env.VITE_BACKEND_URL || "https://url-shortener-psx0.onrender.com",
});

