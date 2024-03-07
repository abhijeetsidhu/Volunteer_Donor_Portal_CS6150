import React, { useState } from 'react';
import Header from './Header';
import { Container, Typography, Card, CardContent, Button, Modal, TextField, IconButton, Grid } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';

function VolunteerPage() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [eventDetails, setEventDetails] = useState({
    id: null,
    name: '',
    description: '',
    location: '',
    dateTime: ''
  });

  // Dummy data for upcoming events
  const [upcomingEvents, setUpcomingEvents] = useState([
    { id: 1, name: 'Event 1', dateTime: '2024-03-05T10:00:00', location: 'Location 1' },
    { id: 2, name: 'Event 2', dateTime: '2024-03-06T12:00:00', location: 'Location 2' },
    { id: 3, name: 'Event 3', dateTime: '2024-03-07T14:00:00', location: 'Location 3' }
  ]);

  // Dummy data for volunteer acknowledgement
  const volunteerAcknowledgement = [
    { id: 1, name: 'John Doe', role: 'Volunteer' },
    { id: 2, name: 'Jane Smith', role: 'Volunteer' },
    { id: 3, name: 'Michael Johnson', role: 'Volunteer' }
  ];

  // Hardcoded user role for testing purposes
  const userRole = 'Admin'; // or 'Staff'

  const handleOpenModal = (event) => {
    setIsModalOpen(true);
    setEventDetails(event);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEventDetails({
      id: null,
      name: '',
      description: '',
      location: '',
      dateTime: ''
    });
  };

  const handleInputChange = (e) => {
    const { id, value } = e.target;
    setEventDetails(prevState => ({
      ...prevState,
      [id]: value
    }));
  };

  const handleEditEvent = () => {
    // Handle event editing logic here, e.g., send data to the backend
    console.log('Event edited:', eventDetails);
    // Close the modal after event editing
    setIsModalOpen(false);
    // Reset event details
    setEventDetails({
      id: null,
      name: '',
      description: '',
      location: '',
      dateTime: ''
    });
  };

  const handleDeleteEvent = (eventId) => {
    // Handle event deletion logic here, e.g., send request to the backend
    console.log('Event deleted:', eventId);
    // Update the upcoming events list by filtering out the deleted event
    setUpcomingEvents(prevEvents => prevEvents.filter(event => event.id !== eventId));
  };

  return (
    <div>
      <Header />

      <Container maxWidth="lg">
        <br />
        {/* Upcoming Events Section */}
        <section>
          <div style={{ display: 'flex', justifyContent: 'space-between' }}>
            <Typography variant="h4" gutterBottom>
              Upcoming Events
            </Typography>
            {(userRole === 'Admin' || userRole === 'Staff') && (
              <Button variant="contained" color="primary" onClick={handleOpenModal} style={{ marginBottom: '20px' }}>
                Create Event
              </Button>
            )}
          </div>
          <div style={{ display: 'flex', justifyContent: 'space-between' }}>
            {upcomingEvents.map(event => (
              <Card key={event.id} style={{ width: '30%', marginBottom: '20px', position: 'relative' }}>
                <CardContent>
                  <Grid container justifyContent="space-between" alignItems="center">
                    <Typography variant="h6">{event.name}</Typography>
                    <div>
                      <IconButton onClick={() => handleOpenModal(event)} aria-label="edit">
                        <EditIcon />
                      </IconButton>
                      <IconButton onClick={() => handleDeleteEvent(event.id)} aria-label="delete">
                        <DeleteIcon />
                      </IconButton>
                    </div>
                  </Grid>
                  <Typography variant="body1">Date & Time: {new Date(event.dateTime).toLocaleString()}</Typography>
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

      {/* Event Creation/Edit Modal */}
      <Modal
        open={isModalOpen}
        onClose={handleCloseModal}
        aria-labelledby="event-creation-modal"
        aria-describedby="event-creation-description"
      >
        <Container maxWidth="sm" sx={{ marginTop: '20vh', bgcolor: 'background.paper', padding: 4 }}>
          <Typography variant="h5" id="event-creation-modal" gutterBottom>
            {eventDetails.id ? 'Edit Event' : 'Create Event'}
          </Typography>
          <TextField
            id="name"
            label="Event Name"
            variant="outlined"
            fullWidth
            value={eventDetails.name}
            onChange={handleInputChange}
            margin="normal"
          />
          <TextField
            id="description"
            label="Event Description"
            variant="outlined"
            fullWidth
            multiline
            rows={4}
            value={eventDetails.description}
            onChange={handleInputChange}
            margin="normal"
          />
          <TextField
            id="location"
            label="Location"
            variant="outlined"
            fullWidth
            value={eventDetails.location}
            onChange={handleInputChange}
            margin="normal"
          />
          <TextField
            id="dateTime"
            label="Date & Time"
            type="datetime-local"
            variant="outlined"
            fullWidth
            value={eventDetails.dateTime}
            onChange={handleInputChange}
            margin="normal"
            InputLabelProps={{
              shrink: true,
            }}
          />
          <Button variant="contained" color="primary" onClick={handleEditEvent} sx={{ mt: 2, width: '100%' }}>
            {eventDetails.id ? 'Save Changes' : 'Create'}
          </Button>
        </Container>
      </Modal>
    </div>
  );
}

export default VolunteerPage;