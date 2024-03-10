import React from 'react';
import Header from './Header';
import { Container, Typography, Card, CardContent, Button } from '@mui/material';

function BeneficiaryPage() {
  
  const isAdmin = true; // Placeholder: Replace with actual admin check

  // Dummy data for beneficiaries
  const beneficiaries = [
    { id: 1, name: 'Beneficiary 1', needs: 'Need 1', location: 'Location 1' },
    { id: 2, name: 'Beneficiary 2', needs: 'Need 2', location: 'Location 2' },
    { id: 3, name: 'Beneficiary 3', needs: 'Need 3', location: 'Location 3' }
  ];

  if (!isAdmin) {
    return <Typography variant="h5">Access Denied</Typography>;
  }

  return (
    <div>
      <Header />

      <Container maxWidth="lg">
        <br />
        {/* Beneficiaries Section */}
        <section>
          <Typography variant="h4" gutterBottom>
            Beneficiaries
          </Typography>
          <div style={{ display: 'flex', justifyContent: 'space-between', flexWrap: 'wrap' }}>
            {beneficiaries.map(beneficiary => (
              <Card key={beneficiary.id} style={{ width: '30%', marginBottom: '20px' }}>
                <CardContent>
                  <Typography variant="h6">{beneficiary.name}</Typography>
                  <Typography variant="body1">Needs: {beneficiary.needs}</Typography>
                  <Typography variant="body1">Location: {beneficiary.location}</Typography>
                </CardContent>
              </Card>
            ))}
          </div>
        </section>
      </Container>
    </div>
  );
}

export default BeneficiaryPage;
