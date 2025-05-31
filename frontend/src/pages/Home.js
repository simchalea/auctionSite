import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Pages.css';
import { getUser } from '../Auth';

export default function Home() {
  const [items, setItems] = useState([]);
  const [search, setSearch] = useState('');
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 10;

  const user = getUser(); 

  useEffect(() => {
    const fetchItems = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/items/active');
        setItems(response.data);
      } catch (error) {
        console.error('Failed to load items:', error);
      } finally {
        setLoading(false);
      }
    };
    fetchItems();
  }, []);

  const filteredItems = items
    .filter(item => item.status === 'active')
    .filter(item => {
    const lowerSearch = search.toLowerCase();
    return (
      !search ||
      (item.title && item.title.toLowerCase().includes(lowerSearch)) ||
      (item.description && item.description.toLowerCase().includes(lowerSearch))
    );
  })
  .sort((a, b) => new Date(b.startDate) - new Date(a.startDate));
  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentItems = filteredItems.slice(indexOfFirstItem, indexOfLastItem);
  const totalPages = Math.ceil(filteredItems.length / itemsPerPage);

  if (loading) {
    return <div className="signin-container">טוען מוצרים...</div>;
  }

  return (
    <div className="search-bar-wrapper" dir="ltr">
      <input
        type="text"
        placeholder="חיפוש חופשי"
        className="search-bar signin-input"
        value={search}
        onChange={e => setSearch(e.target.value)}
      />

      {currentItems.length === 0 ? (
        <p>לא נמצאו מוצרים פעילים תואמים.</p>
      ) : (
        <div className="item-grid">
          {currentItems.map(item => (
            <div key={item.id} className="item-card">
              <p className="item-img-wrapper">
              <img src={`http://localhost:8080${item.imageUrl}`} alt={item.title} className="item-img" />
              </p>
              <h3 className="item-title">{item.title}</h3>
              <p className="item-price">מחיר נוכחי: ₪{item.currentPrice}</p>

              {user ? (
                user.id === item.seller?.id ? (
                  <button
                    onClick={() => window.location.href = `/itemoffers/${item.id}`}
                    className="signin-btn"
                  >
                    הצג הצעות
                  </button>
                ) : (
                  <button
                    onClick={() => window.location.href = `/itemdetails/${item.id}`}
                    className="signin-btn"
                  >
                    הגש הצעה
                  </button>
                )
              ) : (
                <p style={{ fontSize: '0.9rem', color: 'gray' }}>התחבר כדי להגיש הצעה</p>
              )}
            </div>
          ))}
        </div>
      )}

      <div style={{ textAlign: "center", marginTop: "1rem" }}>
        {Array.from({ length: totalPages }, (_, i) => (
          <button
            key={i}
            onClick={() => setCurrentPage(i + 1)}
            className="signin-btn"
            style={{
              margin: "0 5px",
              backgroundColor: currentPage === i + 1 ? "#007bff" : "#e0e0e0",
              color: currentPage === i + 1 ? "white" : "black"
            }}
          >
            {i + 1}
          </button>
        ))}
      </div>
    </div>
  );
}
