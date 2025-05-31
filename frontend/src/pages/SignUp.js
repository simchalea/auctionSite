import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom'; 
import './Pages.css';

function SignUp({ onSuccess }) {
  const navigate = useNavigate(); 
  const [formData, setFormData] = useState({
    email: '',
    password: '',
    phone: '',
    date: ''
  });

  const handleChange = (e) => {
    setFormData((prev) => ({
      ...prev,
      [e.target.name]: e.target.value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const adjustedData = {
      ...formData,
      date: formData.date + 'T00:00:00'
    };

    try {
      const response = await axios.post(
        'http://localhost:8080/api/users/register',
        adjustedData
      );

      // ✅ שמור את המשתמש
      localStorage.setItem("user", JSON.stringify(response.data));

      // ✅ אם יש onSuccess (מודל) - הפעל אותו
      if (onSuccess) {
        onSuccess(); // סוגר את המודל
      } else {
        navigate('/'); // במקרה שאין מודל
      }

    } catch (error) {
      if (error.response) {
        alert('הרשמה נכשלה: ' + error.response.data);
      } else {
        alert('שגיאת רשת: ' + error.message);
      }
    }
  };

  return (
    <div className="signin-container">
      <h2 className="signin-title">הרשמה</h2>
      <form className="signin-form" onSubmit={handleSubmit}>
        <input
          type="email"
          name="email"
          placeholder="דואר אלקטרוני"
          className="signin-input"
          value={formData.email}
          onChange={handleChange}
          required
        />
        <input
          type="password"
          name="password"
          placeholder="סיסמא"
          className="signin-input"
          value={formData.password}
          onChange={handleChange}
          required
        />
        <input
          type="tel"
          name="phone"
          placeholder="טלפון"
          className="signin-input"
          value={formData.phone}
          onChange={handleChange}
          required
          pattern="[0-9]*"
          inputMode="numeric"
        />
        <input
          type="date"
          name="date"
          className="signin-input"
          value={formData.date}
          onChange={handleChange}
          required
        />
        <div className="signin-buttons">
          <button type="submit" className="signin-btn">הרשם</button>
        </div>
      </form>
    </div>
  );
}

export default SignUp;
