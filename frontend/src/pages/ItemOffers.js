import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import './Pages.css';
import { getUser } from '../Auth';
export default function ItemOffers() {
  const { id } = useParams();
  const [item, setItem] = useState(null);
  const [offers, setOffers] = useState([]);
  const user = getUser();
  const [newEndDate, setNewEndDate] = useState('');

  useEffect(() => {
    // Fetch item details
    axios.get(`http://localhost:8080/api/items/${id}`)
      .then(res => setItem(res.data))
      .catch(err => console.error('Failed to load item:', err));

    // Fetch offers for this item
    axios.get(`http://localhost:8080/api/offers/item/${id}`)
      .then(res => setOffers(res.data))
      .catch(err => console.error('Failed to load offers:', err));
  }, [id]);

  if (!item) return <div>טוען פריט...</div>;
const handleUpdateEndDate = async () => {
  console.log("Submitting endDate:", newEndDate);
  try {
    await axios.put(`http://localhost:8080/api/items/${id}/end-date`, {
      endDate: newEndDate
    });
    alert('תאריך הסיום עודכן בהצלחה');
    setItem({ ...item, endDate: newEndDate + 'T00:00:00' }); // לעדכון מקומי מהיר
  } catch (err) {
    console.error(err);
    alert('נכשל עדכון תאריך הסיום');
  }
};
  return (
    <div className="item-details-container" dir="rtl">
      <h2>הצעות עבור: {item.title}</h2>
      {item.imageUrl && (
        <img src={`http://localhost:8080${item.imageUrl}`} alt={item.title} className="item-img" />
      )}
     <p><strong>מחיר נוכחי:</strong> ₪{item.currentPrice}</p>
      <p><strong>תאריך סיום:</strong> {new Date(item.endDate).toLocaleDateString('he-IL')}</p>
      {user?.id === item.seller?.id &&
       item.status === 'active' &&
       new Date() < new Date(item.endDate) && (
  <> 
    <label>עדכן תאריך סיום:</label>
    <input
      type="date"
      className="signin-input"
      value={newEndDate}
      onChange={(e) => setNewEndDate(e.target.value)}
      min={new Date().toISOString().split('T')[0]}
    />
    <button onClick={handleUpdateEndDate} className="signin-btn">עדכן תאריך סיום</button>
  </>
)}

    
      <h3>רשימת הצעות:</h3>
      {offers.length === 0 ? (
        <p>אין הצעות עבור פריט זה.</p>
      ) : (
        <ul>
          {offers.map((offer) => (
            <li key={offer.id}>
              ₪{offer.amount} - הוגשה ב־{new Date(offer.offerTime).toLocaleString('he-IL')}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
