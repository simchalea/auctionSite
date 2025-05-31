import './Pages.css';
import React, { useState } from 'react';
import axios from 'axios';import { useNavigate } from 'react-router-dom';

function Login({ onSuccess, onClose }) {
     const [email, setEmail] = useState('');
     const [password, setPassword] = useState('');
     const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/users/login", {
        email,
        password,
      });
      localStorage.setItem("user", JSON.stringify(response.data));
      console.log("Logged in user:", response.data);
    if (onSuccess) onSuccess();
    navigate("/");
    } catch (error) {
      console.error("Login failed:", error);
      alert("Invalid credentials");
    }
  };
  
 const handleGoToSignup = () => {
  if(onClose)
    onClose();          // סוגר את המודל
    navigate("/signup"); // מעביר לעמוד ההרשמה
  };
  return (

    <div className="signin-container" dir="rtl">
      <h2 className="signin-title">כניסה לחשבון</h2>
      <form className="signin-form" onSubmit={handleLogin}>
       <input className="signin-input" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="שם משתמש" />
      <input className="signin-input" value={password} type="password" onChange={(e) => setPassword(e.target.value)} placeholder="סיסמא" />
        <div className="signin-buttons">
          <button type="submit" className="signin-btn">הכנס</button>
        <button type="button" className="signin-btn" onClick={handleGoToSignup}>אין לך חשבון? הרשם</button>
        </div>
      </form>
    </div>
    
  );
}

export default Login;