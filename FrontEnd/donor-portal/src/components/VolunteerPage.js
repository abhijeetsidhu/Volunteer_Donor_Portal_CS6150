import React from 'react';
import Header from './Header';
import { Container, Typography, Card, CardContent, Button } from '@mui/material';

function VolunteerPage() {
  // Dummy data for upcoming events
  const upcomingEvents = [
    { id: 1, name: 'Event 1', time: '10:00 AM', location: 'Location 1' },
    { id: 2, name: 'Event 2', time: '12:00 PM', location: 'Location 2' },
    { id: 3, name: 'Event 3', time: '2:00 PM', location: 'Location 3' }
  ];

  // Dummy data for volunteer acknowledgement
  const volunteerAcknowledgement = [
    { id: 1, name: 'John Doe', role: 'Volunteer' },
    { id: 2, name: 'Jane Smith', role: 'Volunteer' },
    { id: 3, name: 'Michael Johnson', role: 'Volunteer' }
  ];

  return (
    <div>
      <Header />

      <Container maxWidth="lg">
        <br />
        {/* Upcoming Events Section */}
        <section>
          <Typography variant="h4" gutterBottom>
            Upcoming Events
          </Typography>
          <div style={{ display: 'flex', justifyContent: 'space-between' }}>
            {upcomingEvents.map(event => (
              <Card key={event.id} style={{ width: '30%', marginBottom: '20px' }}>
                <CardContent>
                  <Typography variant="h6">{event.name}</Typography>
                  <Typography variant="body1">Time: {event.time}</Typography>
                  <Typography variant="body1">Location: {event.location}</Typography>
                  <Button variant="contained" color="primary">Sign Up</Button>
                </CardContent>
              </Card>
            ))}
          </div>
        </section>

        {/* Volunteer Acknowledgement Section */}
        <br />
        <section>
          <Typography variant="h4" gutterBottom>
            Volunteer Acknowledgement
          </Typography>
          <div style={{ display: 'flex', justifyContent: 'space-between' }}>
            {volunteerAcknowledgement.map(volunteer => (
              <Card key={volunteer.id} style={{ width: '30%', marginBottom: '20px' }}>
                <CardContent>
                  <Typography variant="h6">{volunteer.name}</Typography>
                  <Typography variant="body1">Role: {volunteer.role}</Typography>
                </CardContent>
              </Card>
            ))}
          </div>
        </section>
      </Container>
    </div>
  );
}

export default VolunteerPage;