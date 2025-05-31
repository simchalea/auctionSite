import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { getUser } from '../Auth';
import './Pages.css';

export default function ItemDetails() {
  const { id } = useParams();
  //const navigate = useNavigate();
  const user = getUser();
  const [item, setItem] = useState(null);
  const [offerAmount, setOfferAmount] = useState('');
  
  useEffect(() => {
    const fetchItem = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/items/${id}`);
        setItem(response.data);
      } catch (error) {
        console.error('Failed to fetch item:', error);
      }
    };
    fetchItem();
  }, [id]);


  const getMinimumIncrement = (startingPrice) => {
    if (startingPrice < 100) return 5;
    else if (startingPrice < 1000) return 10;
    else if (startingPrice < 5000) return 50;
    else return 100;
  };
console.log("Item:", item);
console.log("User:", user);
  const handleOfferSubmit = async () => {
    const minIncrement = getMinimumIncrement(item.startingPrice);
    const requiredMin = (item.currentPrice ?? item.startingPrice) + minIncrement;

    if (parseFloat(offerAmount) < requiredMin) {
      alert(`הצעה חייבת להיות לפחות ₪${minIncrement} מעל למחיר הנוכחי (₪${item.currentPrice ?? item.startingPrice})`);
      return;
    }

    try {
      await axios.post('http://localhost:8080/api/offers', {
  amount: offerAmount,
  itemId: item.id,
  bidderId: user.id
});
      alert('ההצעה נשלחה בהצלחה!');
      setOfferAmount('');
    } catch (error) {
      alert('שליחת ההצעה נכשלה');
      console.error(error);
    }
  };

  if (!item) return <div>טוען פריט...</div>;

  return (
    <div className="item-details-container" dir="rtl">
      <h2>{item.title}</h2>
      <img src={`http://localhost:8080${item.imageUrl}`} alt={item.title} className="item-img" />
      <p>{item.description}</p>
      <p>תאריך התחלה: {new Date(item.startDate).toLocaleDateString('he-IL')}</p>
      <p>תאריך סיום: {new Date(item.endDate).toLocaleDateString('he-IL')}</p>
      <p>מחיר התחלתי: ₪{item.startingPrice}</p>
      <p>מחיר נוכחי: ₪{item.currentPrice ?? item.startingPrice}</p>

      
          <label>סכום הצעה:</label>
          <input
            type="number"
            value={offerAmount}
            onChange={(e) => setOfferAmount(e.target.value)}
            className="signin-input"
          />
          <button onClick={handleOfferSubmit} className="signin-btn">הגש הצעה</button>
        
     
     </div>
    
  );
  
}
