import React from 'react';
import { Container, Typography, TextField, Button, Grid, FormControlLabel, Checkbox } from '@mui/material';
import Header from './Header';

function RegisterPage() {
  return (
    <div>
      <Header />
      <Container maxWidth="sm" sx={{ mt: 4 }}>
        <Typography variant="h4" gutterBottom>Register Page</Typography>
        <Grid container spacing={2}>
          <Grid item xs={12} sm={6}>
            <TextField
              id="firstName"
              label="First Name"
              variant="outlined"
              fullWidth
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              id="lastName"
              label="Last Name"
              variant="outlined"
              fullWidth
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              id="email"
              label="Email"
              type="email"
              variant="outlined"
              fullWidth
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              id="dob"
              label="Date of Birth"
              type="date"
              variant="outlined"
              fullWidth
              InputLabelProps={{
                shrink: true,
              }}
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              id="phoneNumber"
              label="Phone Number"
              variant="outlined"
              fullWidth
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              id="password"
              label="Password"
              type="password"
              variant="outlined"
              fullWidth
            />
          </Grid>
          <Grid item xs={12}>
            <FormControlLabel
              control={<Checkbox color="primary" />}
              label="Send email notifications about volunteer opportunities / upcoming events"
            />
          </Grid>
          <Grid item xs={12}>
            <FormControlLabel
              control={<Checkbox color="primary" />}
              label="Send WhatsApp notifications about volunteer opportunities / upcoming events"
            />
          </Grid>
          <Grid item xs={12}>
            <Button
              variant="contained"
              color="primary"
              fullWidth
              onClick={() => {
                // Handle register logic here
              }}
            >
              Register
            </Button>
          </Grid>
        </Grid>
      </Container>
    </div>
  );
}

export default RegisterPage;