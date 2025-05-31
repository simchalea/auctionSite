# Auction Site Project

This is a full-stack auction site built with:
- **Backend**: Java Spring Boot + PostgreSQL
- **Frontend**: React

## Requirements

- Java 17+
- Node.js + npm
- PostgreSQL (installed locally)

---

## Backend Setup (Spring Boot)

### 1. Configure Database Credentials

Edit the `application.properties` file in `backend/src/main/resources/`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/auction_db
spring.datasource.username= your user
spring.datasource.password=your password
```

> Replace `your user` and `your password with your local PostgreSQL credentials.

### 2. Run the Database Scripts

- Open your PostgreSQL client
- Create the database manually:
  ```sql
  CREATE DATABASE auction_db;
 
- Run both scripts in order:
  1. `schema.sql` – to create tables
  2. `data.sql` – to populate initial data

### 3. Build and Run the Backend

In the `backend` directory:

```bash
./mvnw spring-boot:run
```
This will:
- Download dependencies (including the PostgreSQL driver automatically via `pom.xml`)
- Start the backend server on `http://localhost:8080`

## Frontend Setup (React)

### 1. Navigate to the frontend folder

```bash
cd frontend
```

### 2. Install Dependencies

```bash
npm install
```
This will install:
- `axios` – for API calls
- `react-modal` – for modals

### 3. Start the React App

```bash
npm start
```
This will open the site at `http://localhost:3000`

##  Ready to Use

Now your app should be running with:
- `http://localhost:8080` → Spring Boot API
- `http://localhost:3000` → React UI
