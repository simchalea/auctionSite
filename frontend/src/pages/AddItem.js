import { getUser, isLoggedIn } from "../Auth";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import './Pages.css';
import axios from 'axios';

function AddItem() {
  const navigate = useNavigate();
  const user = getUser();

  useEffect(() => {
    if (!isLoggedIn()) {
      navigate("/login");
    }
  }, [navigate]);

  const [formData, setFormData] = useState({
    title: '',
    description: '',
    startingPrice: '',
    startDate: '',
    endDate: ''
  });

  const [imageFile, setImageFile] = useState(null);
  const [errors, setErrors] = useState({});

  const validate = () => {
    const errs = {};

    if (!formData.title.trim()) errs.title = "יש להזין כותרת";
    if (!formData.description.trim()) errs.description = "יש להזין תיאור";
    if (!imageFile) errs.image = "יש לבחור קובץ תמונה";
    if (!formData.startingPrice || parseFloat(formData.startingPrice) <= 0) {
      errs.startingPrice = "המחיר חייב להיות גדול מ-0";
    }
    if (!formData.startDate) errs.startDate = "יש להזין תאריך התחלה";
    if (!formData.endDate) errs.endDate = "יש להזין תאריך סיום";
    else if (formData.startDate && formData.endDate && formData.endDate < formData.startDate) {
      errs.endDate = "תאריך הסיום חייב להיות אחרי תאריך ההתחלה";
    }

    setErrors(errs);
    return Object.keys(errs).length === 0;
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validate()) return;

    const data = new FormData();
    data.append("title", formData.title);
    data.append("description", formData.description);
    data.append("startingPrice", parseFloat(formData.startingPrice));
    data.append("startDate", formData.startDate + "T00:00:00");
    data.append("endDate", formData.endDate + "T00:00:00");
    data.append("sellerId", user.id);
    data.append("image", imageFile);

    try {
      await axios.post('http://localhost:8080/api/items/upload', data);
      alert("המוצר נוסף בהצלחה!");
      setFormData({
        title: '',
        description: '',
        startingPrice: '',
        startDate: '',
        endDate: ''
      });
      setImageFile(null);
      setErrors({});
    } catch (error) {
      console.error("Error uploading item:", error);
      alert("הוספת המוצר נכשלה");
    }
  };

  return (
    <div className="signin-container" dir="rtl">
      <h2 className="signin-title">הוספת מוצר</h2>
      <form className="signin-form" onSubmit={handleSubmit}>
        <input name="title" placeholder="כותרת" value={formData.title} onChange={handleChange} className="signin-input" />
        {errors.title && <div className="error-text">{errors.title}</div>}

        <textarea name="description" placeholder="תיאור" value={formData.description} onChange={handleChange} className="signin-input" />
        {errors.description && <div className="error-text">{errors.description}</div>}

        <input
          name="image"
          type="file"
          accept="image/*"
          onChange={(e) => setImageFile(e.target.files[0])}
          className="signin-input"
        />
        {errors.image && <div className="error-text">{errors.image}</div>}

        <input name="startingPrice" type="number" placeholder="מחיר התחלתי" value={formData.startingPrice} onChange={handleChange} className="signin-input" />
        {errors.startingPrice && <div className="error-text">{errors.startingPrice}</div>}

        <label className="signin-label">תאריך התחלה</label>
        <input name="startDate" type="date" value={formData.startDate} onChange={handleChange} className="signin-input" />
        {errors.startDate && <div className="error-text">{errors.startDate}</div>}

        <label className="signin-label">תאריך סיום</label>
        <input name="endDate" type="date" value={formData.endDate} onChange={handleChange} className="signin-input" />
        {errors.endDate && <div className="error-text">{errors.endDate}</div>}

        <div className="signin-buttons">
          <button type="submit" className="signin-btn">הוסף מוצר</button>
        </div>
      </form>
    </div>
  );
}

export default AddItem;
