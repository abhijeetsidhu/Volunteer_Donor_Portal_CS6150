// App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import MainPage from './components/MainPage';
import VolunteerPage from './components/VolunteerPage';
import DonorPage from './components/DonorPage';
import AboutMePage from './components/AboutMePage';
import LoginPage from './components/LoginPage';
import RegisterPage from './components/RegisterPage';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/volunteer" element={<VolunteerPage />} />
          <Route path="/donate" element={<DonorPage />} />
          <Route path="/about" element={<AboutMePage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
