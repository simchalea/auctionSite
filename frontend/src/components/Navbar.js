import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { logout, isLoggedIn } from '../Auth';
import './Navbar.css';

function Navbar() {
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/'); // חזרה לעמוד הבית
    window.location.reload(); // רענון הדף כדי להפעיל לוגיקה מחדש
  };

  return (
    <nav className="navbar" dir="rtl">
      <Link to="/" className="nav-link">בית</Link>
      <Link to="/additem" className="nav-link">הוסף פריט</Link>

      {!isLoggedIn() && (
        <Link to="/login" className="nav-link">התחברות/הרשמה</Link>
      )}

      {isLoggedIn() && (
        <>
          <Link to="/user" className="nav-link">פרופיל</Link>
          <button onClick={handleLogout} className="signin-btn">התנתק</button>
        </>
      )}
    </nav>
  );
}

export default Navbar;
