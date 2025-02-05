import React from 'react';
import { Link } from 'react-router-dom';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import appIcon from '../logos/prajna-small-logo.jpeg';

function Header() {
  // Check if user is logged in
  const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
  // Check user role
  const userRole = localStorage.getItem('userRole');
  const userName = localStorage.getItem('userName');

  // Function to handle logout
  const handleLogout = () => {
    // Clear user information from local storage upon logout
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('userRole');
    localStorage.removeItem('userName')
    // Reload the page
    window.location.reload();
  };

  const appUrl = "https://mgt-portal-team-webpage.web.app/";
  const surveyLink = "https://docs.google.com/forms/d/e/1FAIpQLSdLvcprgAIrrEmWcGed--2vPMLXE2RirD94Ix1o4BrUzUETIQ/viewform?usp=sf_link"

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar sx={{ justifyContent: 'space-between' }}>
          <Box sx={{ display: 'flex', alignItems: 'center' }}>
            <Link to="/">
              <IconButton
                size="large"
                edge="start"
                color="inherit"
                aria-label="menu"
                sx={{ mr: 2 }}
              >
                <img src={appIcon} alt="App Icon" style={{ width: '50px', height: '50px' }} />
              </IconButton>
            </Link>
            <Button color="inherit" component={Link} to="/volunteer">BE PART OF THE CHANGE</Button>
            <Button color="inherit" component={Link} to="/donate">SUPPORT US</Button>
            <Button color="inherit" component={Link} to="/about">About Us</Button>
            {(userRole === 'ADMIN' || userRole === 'STAFF') && (
              <Button color="inherit" component={Link} to="/beneficiary">Beneficiary</Button>
            )}
            {userRole === 'ADMIN' && (
              <Button color="inherit" component={Link} to="/admin">Admin</Button>
            )}
          </Box>
          <div className="login-signup">
            <Button color="inherit" href={appUrl} sx={{ color: '#101820', backgroundColor: '#FBEAEB', '&:hover': { backgroundColor: '#d81b60' } }}>Team Webpage</Button>
            <Button color="inherit" href={surveyLink} sx={{ color: '#101820', backgroundColor: '#FBEAEB', '&:hover': { backgroundColor: '#d81b60' } }}>Survey</Button>
            {isLoggedIn ? (
              // Render logout button if user is logged in
              <>
                <span style={{ marginRight: '10px', color: 'white' }}>Hello {userName}</span>
                <Button color="inherit" onClick={handleLogout}>Logout</Button>
              </>
            ) : (
              // Render login and sign up buttons if user is not logged in
              <>
                <Button color="inherit" component={Link} to="/login">Login</Button>
                <Button color="inherit" component={Link} to="/register">Sign Up</Button>
              </>
            )}
          </div>
        </Toolbar>
      </AppBar>
    </Box>
  );
}

export default Header;