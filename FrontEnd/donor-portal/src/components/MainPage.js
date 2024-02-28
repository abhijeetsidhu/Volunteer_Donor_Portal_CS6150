import React, { useState } from 'react'; 
import Header from './Header';
import { Container, Typography, Card, CardContent, Button } from '@mui/material';
import Carousel from 'react-bootstrap/Carousel';

function MainPage() {
  // Dummy data for featured events
  const featuredEvents = [
    { id: 1, name: 'Event 1', time: '10:00 AM', location: 'Location 1' },
    { id: 2, name: 'Event 2', time: '12:00 PM', location: 'Location 2' },
    { id: 3, name: 'Event 3', time: '2:00 PM', location: 'Location 3' }
  ];

  // Dummy data for impact section
  const impactData = [
    { id: 1, description: 'Testimonial Description', volunteer: '- John Doe, Volunteer' },
    { id: 2, description: 'Testimonial Description', volunteer: '- John Doe, Volunteer' },
    { id: 3, description: 'Testimonial Description', volunteer: '- John Doe, Volunteer' },
  ];

  const [index, setIndex] = useState(0); 
    const handleSelect = (selectedIndex) => { 
        setIndex(selectedIndex);}

  return (
    <div>
      <Header />
      <Container maxWidth="lg">
        {/* Carousel Section */}
        <section>
          <br/>
          <Carousel>
            <Carousel.Item interval={1000}>
              <img className="d-block w-100"
                         src= "https://freevolunteering.net/wp-content/uploads/2016/05/FV-image-1.jpg"
                         alt="One" 
              />
            </Carousel.Item>
            <Carousel.Item interval={1000}>
              <img className="d-block w-100"
                         src= "https://images.indianexpress.com/2020/04/food-4.jpg?w=414"
                         alt="two" 
              />
            </Carousel.Item>
            <Carousel.Item interval={1000}>
              <img className="d-block w-100"
                         src= "https://devxchange.org/wp-content/uploads/2019/09/devx2-1-1-768x576-768x422.jpg"
                         alt="three" 
              />
            </Carousel.Item>
          </Carousel>
        </section>

        {/* Featured Events Section */}
        <br/>
        <section>
          <Typography variant="h4" gutterBottom>
            Featured Events
          </Typography>
          <div style={{ display: 'flex', justifyContent: 'space-between' }}>
            {featuredEvents.map(event => (
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

        {/* Impact Section */}
        <br/>
        <section>
          <Typography variant="h4" gutterBottom>
            Impact Section
          </Typography>
          <div style={{ display: 'flex', justifyContent: 'space-between' }}>
            {impactData.map(data => (
              <Card key={data.id} style={{ width: '30%', marginBottom: '20px' }}>
                <CardContent>
                  <Typography variant="body1">
                    {data.description}
                  </Typography>
                  <Typography variant="body2" color="textSecondary">
                    {data.volunteer}
                  </Typography>
                </CardContent>
              </Card>
            ))}
          </div>
        </section>

      </Container>
    </div>
  );
}

export default MainPage;