import React, { useState, useEffect } from 'react';
import Header from './Header';
import { Container, Typography, Card, CardContent, Button, Modal, TextField, IconButton, Grid } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import EventServices from '../services/EventServices'; // Import EventServices module

function VolunteerPage() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [eventDetails, setEventDetails] = useState({
    eventId: '', // New field
    eventName: '',
    description: '',
    location: '',
    date: ''
  });

  const [upcomingEvents, setUpcomingEvents] = useState([]);

  const volunteerAcknowledgement = [
    { id: 1, name: 'John Doe', role: 'Volunteer' },
    { id: 2, name: 'Jane Smith', role: 'Volunteer' },
    { id: 3, name: 'Michael Johnson', role: 'Volunteer' }
  ];

  const userRole = 'Admin'; // or 'Staff'

  useEffect(() => {
    fetchEvents();
  }, []);

  const fetchEvents = async () => {
    try {
      const events = await EventServices.getAllEvents();
      setUpcomingEvents(events.events);
    } catch (error) {
      console.error('Error fetching events:', error);
    }
  };

  const handleOpenModal = (event) => {
    setIsModalOpen(true);
    setEventDetails({
      eventId: event.eventId, // Set eventId
      eventName: event.eventName,
      description: event.description,
      location: event.location,
      date: event.date
    });
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEventDetails({
      eventId: '', // Reset eventId
      eventName: '',
      description: '',
      location: '',
      date: ''
    });
  };

  const handleInputChange = (e) => {
    const { id, value } = e.target;
    setEventDetails(prevState => ({
      ...prevState,
      [id]: value
    }));
  };

  const handleEditEvent = async () => {
    try {
      if (eventDetails.eventId) {
        const response = await EventServices.updateEvent(eventDetails.eventId, eventDetails);
        console.log('Event updated:', response);
      } else {
        const response = await EventServices.createEvent(eventDetails);
        console.log('Event created:', response);
      }
      handleCloseModal();
      fetchEvents(); // Refetch events after creation or update
    } catch (error) {
      console.error('Error creating/editing event:', error);
    }
  };

  const handleDeleteEvent = async (eventId) => {
    try {
      console.log('Event deleted:', eventId);
      const response = await EventServices.deleteEvent(eventId);
      console.log('Event deletion response:', response);
      setUpcomingEvents(prevEvents => prevEvents.filter(event => event.eventId !== eventId));
    } catch (error) {
      console.error('Error deleting event:', error);
    }
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
              <Button variant="contained" color="primary" onClick={() => setIsModalOpen(true)} style={{ marginBottom: '20px' }}>
                Create Event
              </Button>
            )}
          </div>
          <Grid container spacing={3}>
            {upcomingEvents.map((event, index) => (
              <Grid item xs={12} sm={6} md={4} key={index}>
                <Card style={{ marginBottom: '20px', position: 'relative' }}>
                  <CardContent>
                    <Typography variant="h6">{event.eventName}</Typography>
                    <div style={{ position: 'absolute', top: '0', right: '0' }}>
                      <IconButton onClick={() => handleOpenModal(event)} aria-label="edit">
                        <EditIcon />
                      </IconButton>
                      <IconButton onClick={() => handleDeleteEvent(event.eventId)} aria-label="delete">
                        <DeleteIcon />
                      </IconButton>
                    </div>
                    <Typography variant="body1">Date & Time: {new Date(event.date).toLocaleString()}</Typography>
                    <Typography variant="body1">Location: {event.location}</Typography>
                    <Button variant="contained" color="primary">Sign Up</Button>
                  </CardContent>
                </Card>
              </Grid>
            ))}
          </Grid>
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
            {eventDetails.eventId ? 'Edit Event' : 'Create Event'}
          </Typography>
          <TextField
            id="eventName"
            label="Event Name"
            variant="outlined"
            fullWidth
            value={eventDetails.eventName}
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
            id="date"
            label="Date & Time"
            type="datetime-local"
            variant="outlined"
            fullWidth
            value={eventDetails.date}
            onChange={handleInputChange}
            margin="normal"
            InputLabelProps={{
              shrink: true,
            }}
          />
          <Button variant="contained" color="primary" onClick={handleEditEvent} sx={{ mt: 2, width: '100%' }}>
            {eventDetails.eventId ? 'Save Changes' : 'Create'}
          </Button>
        </Container>
      </Modal>
    </div>
  );
}

export default VolunteerPage;