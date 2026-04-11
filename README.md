# 🔗 Linklytics – Scalable URL Shortener (Bitly Clone)

Linklytics is a full-stack URL shortening platform that allows users to generate short links, redirect them efficiently, and manage URLs using a secure, scalable, and cloud-based backend.

---

## 🌐 Live Demo

- 🔗 Frontend: https://linklytics1.netlify.app  
- ⚙️ Backend API: https://url-shortener-psx0.onrender.com  

---

## 📌 Features

- 🔗 Generate short URLs from long links
- 🚀 Fast redirection using unique short codes
- 🔐 Secure authentication using JWT & Spring Security
- 📡 RESTful API architecture
- ☁️ Cloud-hosted database using Supabase (PostgreSQL)
- 💾 Efficient and scalable data storage
- 🌍 Fully deployed (Frontend + Backend)

---

## 🛠️ Tech Stack

### Backend
- Java
- Spring Boot
- Spring Security
- Hibernate / JPA
- REST APIs

### Frontend
- React.js
- Tailwind CSS
- Vite

### Database
- Supabase (PostgreSQL)

### Deployment
- Backend: Render
- Frontend: Netlify

---

## 🗄️ Database & Backend Services

- Used Supabase as a managed PostgreSQL database service
- Enabled scalable and cloud-based data storage
- Optimized database queries for fast URL lookup and redirection
- Integrated backend APIs with cloud database for real-time operations

---

## ⚙️ How It Works

1. User submits a long URL  
2. Backend generates a unique short code  
3. Short URL is stored in Supabase database  
4. When accessed, backend fetches original URL  
5. User is redirected instantly  

---

## 🔐 Authentication

- Implemented JWT-based authentication  
- Secured API endpoints using Spring Security  
- Stateless session management  

---

## 📂 Project Structure
