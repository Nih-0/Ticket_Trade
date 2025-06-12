# Ticket Trade Backend 🎟️ (Spring Boot API)

This is the **Spring Boot backend** for [Ticket Trade Frontend](https://github.com/Nih-0/Ticket_TradeFrontend), a platform to **buy, list, and exchange tickets** for trains, buses, events, and movies. The backend provides secure REST APIs, JWT-based authentication, and integrates with a MySQL database.

## 🚀 Features

- ✅ User Authentication (JWT + Spring Security)
- 🔐 Role-based access control
- 🧾 CRUD APIs for ticket listing and purchases
- 📦 Image upload support for ticket images
- 🧠 Category-specific logic (Train, Movie, Event, Bus)
- 📤 Email support via EmailJS (connected via frontend)
- 🗃️ MySQL database integration

---

## 🛠️ Tech Stack

- **Backend**: Spring Boot, Spring MVC, Spring Security, Hibernate
- **Database**: MySQL
- **Build Tool**: Maven
- **Authentication**: JWT (JSON Web Token)
- **Dev Tools**: Postman, Swagger (optional), Docker (optional)

---

## 📁 Project Structure

src/
├── config/ # Security & JWT config
├── controller/ # REST API controllers
├── model/ # Entity classes
├── repository/ # JPA Repositories
├── service/ # Business logic
├── dto/ # Data transfer objects (if used)
└── utils/ # Utility classes (token generation, etc.)


---

## 🔐 Authentication Flow (JWT)

- **Signup/Login** → Generates JWT Token
- Token is used in `Authorization` header as:  
  `Bearer <your-token>`
- Backend validates token for protected routes

---

## 📦 APIs Overview

| Method | Endpoint              | Description                  | Auth Required |
|--------|-----------------------|------------------------------|---------------|
| POST   | `/api/auth/signup`    | Register a new user          | ❌            |
| POST   | `/api/auth/login`     | Login & get JWT token        | ❌            |
| GET    | `/api/tickets`        | Get all available tickets    | ✅            |
| POST   | `/api/tickets`        | Add new ticket               | ✅            |
| PUT    | `/api/tickets/{id}`   | Update ticket by ID          | ✅            |
| DELETE | `/api/tickets/{id}`   | Delete ticket by ID          | ✅            |

> More endpoints can be added as needed.

---

## 🧠 Ticket Categories & Business Logic

- **Train**: Train No, Coach, Seat No, Quota, Passenger Name
- **Bus**: Pickup, Drop, Booking ID, Bus Name
- **Movie**: Seat, Screen, Booking ID
- **Event**: Type, People Capacity, Ticket Code

Each category may have different required fields, validated at the backend.

---

## 📄 Run Locally

### 1. Clone the Repo

```bash
git clone https://github.com/Nih-0/Ticket_Trade.git
cd Ticket_Trade

