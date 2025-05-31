import Modal from 'react-modal';
import { useEffect, useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import AddItem from './pages/AddItem';
import ItemDetails from './pages/ItemDetails';
import ItemOffers from './pages/ItemOffers';
import Login from './pages/Login';
import SignUp from './pages/SignUp';
import User from './pages/User';
import PrivateRoute from './components/PrivateRoute';
import { isLoggedIn } from './Auth';
import './App.css';

Modal.setAppElement('#root');

function App() {
  const [showModal, setShowModal] = useState(false);
  const [showSignup, setShowSignup] = useState(false);
  //const [showLogin, setShowLogin] = useState(false);
 
   useEffect(() => {
  const currentPath = window.location.pathname;
  if (!isLoggedIn() && currentPath === '/') {
    setShowModal(true);
  } else {
    setShowModal(false);
  }
}, []);

  const handleLoginSuccess = () => {
    setShowModal(false);
    window.location.reload(); 
  };

 

  const handleCloseModal = () => {
    setShowModal(false);
  };

  return (
    <Router>
      <Navbar />

      <Modal
        isOpen={showModal}
        onRequestClose={handleCloseModal}
        shouldCloseOnOverlayClick={true}
        shouldCloseOnEsc={true}
        contentLabel="Authentication"
        className="Modal"
        overlayClassName="Overlay"
      >
        <button onClick={handleCloseModal} className='modal-close-button'>❌</button>

        {showSignup ? (
          <SignUp onSuccess={handleLoginSuccess} />
        ) : (
          <Login onSuccess={handleLoginSuccess} onSwitch={() => setShowSignup(true)} onClose={handleCloseModal} />

        )}
      </Modal>

      <div className="p-4">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/home" element={<PrivateRoute><Home /></PrivateRoute>} />
          <Route path="/additem" element={<AddItem />} />
          <Route path="/itemdetails/:id" element={<ItemDetails />} />
          <Route path="/itemoffers/:id" element={<ItemOffers />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="/user" element={<User />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
