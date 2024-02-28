import React, { useState } from 'react';
import Header from './Header';
import { Container, Typography, Card, CardContent, Button, Grid, Modal, Box, TextField, Select, MenuItem, FormControl, InputLabel, FormControlLabel, FormLabel, Radio, RadioGroup } from '@mui/material';

function DonorPage() {
  // Dummy data for donation options
  const donationOptions = [
    { id: 1, category: 'Education', description: 'Support education for underprivileged children' },
    { id: 2, category: 'Healthcare', description: 'Provide healthcare services to those in need' },
    { id: 3, category: 'Environment', description: 'Contribute to environmental conservation efforts' }
  ];

  // Dummy data for donation acknowledgement
  const donationAcknowledgement = [
    { id: 1, name: 'John Doe', amount: '$50' },
    { id: 2, name: 'Jane Smith', amount: '$100' },
    { id: 3, name: 'Michael Johnson', amount: '$75' }
  ];

  // State for controlling the modal
  const [openModal, setOpenModal] = useState(false);
  // State to hold the selected donation category
  const [selectedCategory, setSelectedCategory] = useState(null);
  // State to hold the selected payment method
  const [selectedPaymentMethod, setSelectedPaymentMethod] = useState('');
  // State to hold the donation amount
  const [donationAmount, setDonationAmount] = useState('10');
  // State to hold custom donation amount
  const [customDonationAmount, setCustomDonationAmount] = useState('');

  // Function to handle donation button click
  const handleDonate = (category) => {
    setSelectedCategory(category);
    setOpenModal(true);
  };

  // Function to handle modal close
  const handleCloseModal = () => {
    setOpenModal(false);
  };

  return (
    <div>
      <Header />
      <Container maxWidth="lg">
        <br />
        {/* Donation Options Section */}
        <section>
          <Typography variant="h4" gutterBottom>
            Donation Options
          </Typography>
          <Grid container spacing={3}>
            {donationOptions.map(option => (
              <Grid item xs={12} sm={6} md={4} key={option.id}>
                <Card>
                  <CardContent>
                    <Typography variant="h6">{option.category}</Typography>
                    <Typography variant="body1">{option.description}</Typography>
                    <Button variant="contained" color="primary" style={{ marginTop: '10px' }} onClick={() => handleDonate(option.category)}>
                      Donate
                    </Button>
                  </CardContent>
                </Card>
              </Grid>
            ))}
          </Grid>
        </section>

        {/* Donation Acknowledgement Section */}
        <br />
        <section>
          <Typography variant="h4" gutterBottom>
            Donation Acknowledgement
          </Typography>
          <div style={{ display: 'flex', justifyContent: 'space-between' }}>
            {donationAcknowledgement.map(donation => (
              <Card key={donation.id} style={{ width: '30%', marginBottom: '20px' }}>
                <CardContent>
                  <Typography variant="h6">{donation.name}</Typography>
                  <Typography variant="body1">Amount: {donation.amount}</Typography>
                </CardContent>
              </Card>
            ))}
          </div>
        </section>
      </Container>
      
      {/* Donation Confirmation Modal */}
      <Modal
        open={openModal}
        onClose={handleCloseModal}
        aria-labelledby="donation-confirmation-modal"
        aria-describedby="donation-confirmation-description"
      >
        <Box sx={{
          position: 'absolute',
          top: '50%',
          left: '50%',
          transform: 'translate(-50%, -50%)',
          bgcolor: 'background.paper',
          borderRadius: '8px',
          p: 3,
          outline: 'none'
        }}>
          <Typography id="donation-confirmation-modal" variant="h6" component="h2" gutterBottom>
            Donation Confirmation
          </Typography>
          <Typography id="donation-confirmation-description" variant="body1" gutterBottom>
            Are you sure you want to donate {donationAmount === 'other' ? customDonationAmount : `$${donationAmount}`} to {selectedCategory} via {selectedPaymentMethod}?
          </Typography>
          <FormControl component="fieldset" style={{ marginBottom: '20px' }}>
            <FormLabel component="legend">Donation Amount</FormLabel>
            <RadioGroup aria-label="donation-amount" name="donation-amount" value={donationAmount} onChange={(e) => setDonationAmount(e.target.value)}>
              <FormControlLabel value="10" control={<Radio />} label="$10" />
              <FormControlLabel value="20" control={<Radio />} label="$20" />
              <FormControlLabel value="50" control={<Radio />} label="$50" />
              <FormControlLabel value="other" control={<Radio />} label="Other" />
            </RadioGroup>
            {donationAmount === 'other' && (
              <TextField
                label="Other Amount"
                variant="outlined"
                fullWidth
                value={customDonationAmount}
                onChange={(e) => setCustomDonationAmount(e.target.value)}
                style={{ marginTop: '10px' }}
              />
            )}
          </FormControl>
          <FormControl fullWidth style={{ marginBottom: '20px' }}>
            <InputLabel id="payment-method-label">Payment Method</InputLabel>
            <Select
              labelId="payment-method-label"
              id="payment-method-select"
              value={selectedPaymentMethod}
              onChange={(e) => setSelectedPaymentMethod(e.target.value)}
              label="Payment Method"
            >
              <MenuItem value="Credit Card">Credit Card</MenuItem>
              <MenuItem value="PayPal">PayPal</MenuItem>
              <MenuItem value="Stripe">Stripe</MenuItem>
            </Select>
          </FormControl>
          <Button variant="contained" color="primary" onClick={handleCloseModal}>
            Confirm
          </Button>
          <Button variant="contained" color="secondary" onClick={handleCloseModal}>
            Cancel
          </Button>
        </Box>
      </Modal>
    </div>
  );
}

export default DonorPage;