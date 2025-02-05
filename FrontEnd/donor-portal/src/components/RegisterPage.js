import React, { useState } from 'react';
import { Container, Typography, TextField, Button, Grid, FormControlLabel, Checkbox, Alert } from '@mui/material';
import Header from './Header';
import { registerUser } from '../services/UserServices';
import { useNavigate } from 'react-router-dom'; // Import useNavigate
import { AES, enc } from 'crypto-js';
import { PhoneInput } from 'react-international-phone';
import 'react-international-phone/style.css';
import { PhoneNumberUtil } from 'google-libphonenumber';

const phoneUtil = PhoneNumberUtil.getInstance();

const isPhoneValid = (phone) => {
  try {
    return phoneUtil.isValidNumber(phoneUtil.parseAndKeepRawInput(phone));
  } catch (error) {
    return false;
  }
};

function RegisterPage() {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    dob: '',
    role: 'MEMBER',
    phoneNumber: '',
    password: '',
    emailNotification: false,
    whatsappNotification: false
  });
  const [error, setError] = useState('');
  const navigate = useNavigate(); // Initialize useNavigate

  const key = enc.Hex.parse('000102030405060708090a0b0c0d0e0f'); // Example key (128 bits)
  const iv = enc.Hex.parse('101112131415161718191a1b1c1d1e1f');  // Example IV (128 bits)

  const handleChange = (e) => {
    const { id, value, type, checked } = e.target;
  
    if (id === 'firstName') {
      setFormData(prevState => ({
        ...prevState,
        name: `${value} ${formData.lastName}`,
        [id]: value
      }));
    } else if (id === 'lastName') {
      setFormData(prevState => ({
        ...prevState,
        name: `${formData.firstName} ${value}`,
        [id]: value
      }));
    } else {
      setFormData(prevState => ({
        ...prevState,
        [id]: type === 'checkbox' ? checked : value
      }));
    }
  };

  const handlePhoneChange = (value) => {
    // Update the phone number in formData
    setFormData(prevState => ({
      ...prevState,
      phoneNumber: value
    }));
  };

  const handleSubmit = async () => {
    // Clear previous errors
    setError('');
  
    // Validate form fields
    if (!formData.name || !formData.email || !formData.dob || !formData.phoneNumber || !formData.password) {
      setError('Please fill in all fields.');
      return;
    }
  
    // Validate email format
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(formData.email)) {
      setError('Please enter a valid email address.');
      return;
    }
  
    // Validate phone number format
    if (!isPhoneValid(formData.phoneNumber)) {
      setError('Please enter a valid phone number.');
      return;
    }
  
    try {
      // Encrypt the password
      const encryptedPassword = AES.encrypt(formData.password, key, { iv }).toString();
      
      // Update the formData with the encrypted password
      const updatedFormData = { ...formData, password: encryptedPassword };
  
      const response = await registerUser(updatedFormData);
      // Check if the response indicates successful registration
      if (response.logMessage.includes("MEMBER profile created")) {
        // Set local storage upon successful sign up
        localStorage.setItem('isLoggedIn', true);
        localStorage.setItem('userRole', 'MEMBER'); // Assuming 'MEMBER' is a string value
        localStorage.setItem('userName', updatedFormData.name);
        // Redirect to home page upon successful login
        navigate('/');
      } else {
        // Handle registration failure
        setError('Error registering. Please try again later.');
      }
    } catch (error) {
      console.error('Error registering:', error);
      setError('Error registering. Please try again later.');
    }
  };  

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
              value={formData.firstName}
              onChange={handleChange}
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              id="lastName"
              label="Last Name"
              variant="outlined"
              fullWidth
              value={formData.lastName}
              onChange={handleChange}
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              id="email"
              label="Email"
              type="email"
              variant="outlined"
              fullWidth
              value={formData.email}
              onChange={handleChange}
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
              value={formData.dob}
              onChange={handleChange}
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            {/* <TextField
              id="phoneNumber"
              label="Phone Number"
              variant="outlined"
              fullWidth
              value={formData.phoneNumber}
              onChange={handleChange}
            /> */}
            <PhoneInput
              placeholder="Enter phone number"
              value={formData.phoneNumber}
              onChange={handlePhoneChange}
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              id="password"
              label="Password"
              type="password"
              variant="outlined"
              fullWidth
              value={formData.password}
              onChange={handleChange}
            />
          </Grid>
          <Grid item xs={12}>
            <FormControlLabel
              control={<Checkbox id="emailNotification" color="primary" onChange={handleChange} />}
              label="Send email notifications about volunteer opportunities / upcoming events"
            />
          </Grid>
          <Grid item xs={12}>
            <FormControlLabel
              control={<Checkbox id="whatsappNotification" color="primary" onChange={handleChange} />}
              label="Send WhatsApp notifications about volunteer opportunities / upcoming events"
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
              onClick={handleSubmit}
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