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

Linklytics/
│── backend/
│   ├── src/main/java/com/url/shortener/
│   │   ├── controller/
│   │   │   ├── AuthController.java          # Handles login & registration
│   │   │   ├── UrlMappingController.java    # URL shortening & user URLs
│   │   │   └── RedirectController.java      # Handles redirection
│   │   │
│   │   ├── dtos/
│   │   │   ├── LoginRequest.java
│   │   │   ├── RegisterRequest.java
│   │   │   ├── UrlMappingDTO.java
│   │   │   └── ClickEventDTO.java           # Analytics data
│   │   │
│   │   ├── models/                          # Entity classes
│   │   ├── repository/                      # JPA repositories
│   │   ├── service/                         # Business logic layer
│   │   │
│   │   ├── security/
│   │   │   ├── jwt/
│   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   └── JwtUtils.java
│   │   │   └── WebSecurityConfig.java       # Spring Security config
│   │   │
│   │   └── UrlShortenerSbApplication.java
│   │
│   └── resources/
│       ├── application.properties
│       └── application-prod.properties
│
│── frontend/
│   ├── src/
│   │   ├── components/
│   │   │   ├── Dashboard/                   # Analytics UI
│   │   │   ├── LandingPage.jsx
│   │   │   ├── LoginPage.jsx
│   │   │   ├── RegisterPage.jsx
│   │   │   ├── ShortenUrlPage.jsx
│   │   │   ├── NavBar.jsx
│   │   │   └── Footer.jsx
│   │   │
│   │   ├── api/                             # API integration layer
│   │   ├── contextApi/                      # Global state management
│   │   ├── hooks/                           # Custom React hooks
│   │   ├── utils/                           # Helper functions
│   │   │
│   │   ├── App.jsx
│   │   ├── AppRouter.jsx
│   │   └── PrivateRoute.jsx                 # Protected routes (JWT)
│   │
│   └── .env
│
│── pom.xml
│── package.json
│── README.md



---

## Additional Details

### Analytics Dashboard

- Tracks number of clicks per short URL  
- Stores timestamp of each visit  
- Provides user-specific analytics  
- Displays data in dashboard  

Working:
- Each redirect creates a click record  
- Data stored in PostgreSQL (Supabase)  
- Backend processes analytics  
- Frontend displays results  

---

### API Endpoints

Authentication:
- POST /auth/register  
- POST /auth/login  

URL Features:
- POST /api/url/shorten  
- GET /{shortCode}  
- GET /api/url/myurls  

Analytics:
- GET /api/analytics/{shortUrl}  

---

### Setup Instructions

#### Backend Setup (Spring Boot)

Go to backend folder:

Create your own `application.properties` file:

spring.datasource.url=your_db_url  
spring.datasource.username=your_db_username  
spring.datasource.password=your_db_password  

jwt.secret=your_secret_key  
jwt.expiration=86400000  

Run backend:

mvn spring-boot:run  

---

#### Frontend Setup (React)

Go to frontend folder:

cd frontend  

Install dependencies:

npm install  

Create `.env` file:

VITE_API_BASE_URL=http://localhost:8080  

Run frontend:

npm run dev  

---

### Production

Frontend deployed on Netlify  
Backend deployed on Render  
Database hosted on Supabase  

---

### Notes

- application.properties is not included for security reasons  
- Never push API keys or secrets to GitHub  
- Ensure backend is running before frontend  
- Update API base URL for production  

---

### Author

Shoaib Akhtar  

GitHub: https://github.com/shoaibakht34-wq
