import { getUser } from '../Auth';
import React, { useEffect, useState, useCallback } from 'react';
import axios from 'axios';

import './Pages.css';

export default function User() {
  const user = getUser();
  const [userData] = useState(getUser());
  const [openAuctions, setOpenAuctions] = useState([]);
  const [editMode, setEditMode] = useState(false);
  const [password, setPassword] = useState('');
  const [closedAuctionsWithUsers, setClosedAuctionsWithUsers] = useState([]);
  const [wonAuctionsWithSellers, setWonAuctionsWithSellers] = useState([]);

  const fetchUserAuctions = useCallback(async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/users/${userData.id}/auctions`);
      setOpenAuctions(response.data.open);
    } catch (err) {
      console.error('Error loading auctions', err);
    }
  }, [userData.id]);

  useEffect(() => {
    fetchUserAuctions();
  }, [fetchUserAuctions]);

  const handleUpdate = async () => {
    if (!password.trim()) {
      alert('הסיסמה לא יכולה להיות ריקה');
      return;
    }
    try {
      await axios.put(`http://localhost:8080/api/users/${userData.id}`, { password: password });
      alert('הסיסמה עודכנה');
      setEditMode(false);
    } catch (err) {
      alert('שגיאה בעדכון');
    }
  };

  useEffect(() => {
    const fetchClosedAuctions = async () => {
      try {
        const [closed, won] = await Promise.all([
          axios.get(`http://localhost:8080/api/users/${userData.id}/closed-auctions`),
          axios.get(`http://localhost:8080/api/users/${userData.id}/won-auctions`)
        ]);
        setClosedAuctionsWithUsers(closed.data);
        setWonAuctionsWithSellers(won.data);
      } catch (error) {
        console.error("Failed to fetch auctions with users:", error);
      }
    };

    fetchClosedAuctions();
  }, [userData.id]);

  return (
    <div className="profile-container" dir="rtl">
      <h2>פרופיל משתמש</h2>
      <p>שם משתמש:{user.email}</p>
      <p>טלפון:{user.phone}</p>

      {editMode ? (
        <>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="סיסמה חדשה"
          />
          <button onClick={handleUpdate}>שמור</button>
        </>
      ) : (
        <button onClick={() => setEditMode(true)}>שנה סיסמה</button>
      )}

      <h3>מכרזים פתוחים</h3>
      <div className="auction-grid"> 
        {openAuctions.length === 0 ? (
        <p>אין ברשותך מכרזים פתוחים .</p>
      ) : (
        openAuctions.map(item => (
          <div key={item.id} className="auction-card">
            <h4>{item.title}</h4>
            <p>מחיר נוכחי: ₪{item.currentPrice}</p>
          </div>
        ))
    )}
      </div>

      <h3>מכרזים סגורים שבהם אני המוכר</h3>
      {closedAuctionsWithUsers.length === 0 ? (
        <p>אין מכרזים סגורים.</p>
      ) : (
        closedAuctionsWithUsers.map((item, index) => (
          <div key={index} className="auction-card">
            <p><strong>{item.title}</strong> </p>
            <p><strong>מחיר סופי:</strong> ₪{item.currentPrice}</p>
            <p><strong>זוכה:</strong> {item.winner?.username || 'אין זוכה'} | טלפון: {item.winner?.phone || '-'}</p>
          </div>
        ))
      )}

      <h3>מכרזים שזכיתי בהם</h3>
      {wonAuctionsWithSellers.length === 0 ? (
        <p>לא זכית במכרזים.</p>
      ) : (
        wonAuctionsWithSellers.map((item, index) => (
          <div key={index} className="auction-card">
            <p><strong>{item.title}</strong> </p>
            <p><strong>מחיר זכייה:</strong> ₪{item.currentPrice}</p>
            <p><strong>תאריך סיום:</strong> {new Date(item.endDate).toLocaleDateString('he-IL')}</p>
            <p><strong>מוכר:</strong> {item.seller?.username || 'לא ידוע'} | טלפון: {item.seller?.phone || '-'}</p>
          </div>
        ))
      )}
    </div>
  );
}
