import React, { useState } from 'react';
import { Container, Typography, TextField, Button, Grid } from '@mui/material';
import Header from './Header';

function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = () => {
    // Here you can handle login logic, such as sending the email and password to a server for authentication
    console.log('Email:', email);
    console.log('Password:', password);
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