import React, { useState } from 'react';
import { Container, Typography, TextField, Button, Grid, Alert } from '@mui/material';
import Header from './Header';
import { loginUser } from '../services/UserServices';
import { useNavigate } from 'react-router-dom'; // Import useNavigate
import { AES, enc } from 'crypto-js';

function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);
  const navigate = useNavigate(); // Initialize useNavigate

  const key = enc.Hex.parse('000102030405060708090a0b0c0d0e0f'); // Example key (128 bits)
  const iv = enc.Hex.parse('101112131415161718191a1b1c1d1e1f');  // Example IV (128 bits)

  const handleLogin = async () => {
    // Clear previous errors
    setError(null);
  
    // Validate email and password
    if (!email || !password) {
      setError('Please enter both email and password.');
      return;
    }
  
    try {
      // Encrypt the password
      const encryptedPassword = AES.encrypt(password, key, { iv }).toString();
      // console.log("normal pass", password)
      // console.log("encrypted pass", encryptedPassword)
      const response = await loginUser(email, encryptedPassword); // Pass the encrypted password to the login service
      console.log('Login response:', response);
      if (response.logMessage === 'Success!') {
        // Store user information in local storage
        localStorage.setItem('isLoggedIn', true);
        localStorage.setItem('userRole', response.users[0].role);
  
        // Redirect to home page upon successful login
        navigate('/');
      } else if (response.logMessage === 'Incorrect Credentials: Email') {
        setError('Incorrect email.');
      } else if (response.logMessage === 'Incorrect Credentials: Password') {
        setError('Incorrect password.');
      } else {
        setError('An error occurred.');
      }
    } catch (error) {
      console.error('Error logging in:', error);
      // Handle error, such as displaying an error message to the user
      setError('Invalid email or password.');
    }
  };
  

  return (
    <div>
      <Header />
      <Container maxWidth="sm" sx={{ mt: 4 }}>
        <Typography variant="h4" gutterBottom>Login Page</Typography>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <TextField
              id="email"
              label="Email"
              variant="outlined"
              fullWidth
              autoFocus
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              id="password"
              label="Password"
              type="password"
              variant="outlined"
              fullWidth
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </Grid>
          {error && (
            <Grid item xs={12}>
              <Alert severity="error">{error}</Alert>
            </Grid>
          )}
          <Grid item xs={12}>
            <Button
              variant="contained"
              color="primary"
              fullWidth
              onClick={handleLogin}
            >
              Login
            </Button>
          </Grid>
        </Grid>
      </Container>
    </div>
  );
}

export default LoginPage;