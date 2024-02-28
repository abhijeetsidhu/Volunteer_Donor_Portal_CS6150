import React from 'react';
import { Link } from 'react-router-dom';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import appIcon from '../logos/ngoLogo.png';

function Header() {
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
                <img src={appIcon} alt="App Icon" style={{ width: '40px', height: '40px' }} />
              </IconButton>
            </Link>
            <Button color="inherit" component={Link} to="/volunteer">Volunteer Now</Button>
            <Button color="inherit" component={Link} to="/donate">Donate Now</Button>
            <Button color="inherit" component={Link} to="/about">About Us</Button>
          </Box>
          <div className="login-signup"> {/* Login and Sign Up buttons */}
            <Button color="inherit" component={Link} to="/login">Login</Button>
            <Button color="inherit" component={Link} to="/register">Sign Up</Button>
          </div>
        </Toolbar>
      </AppBar>
    </Box>
  );
}

export default Header;