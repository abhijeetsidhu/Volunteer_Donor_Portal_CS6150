import React from 'react';
import { Container, Typography, Grid, Card, CardContent, List, ListItem, ListItemText } from '@mui/material';
import Header from './Header';

function AboutUsPage() {
  return (
    <div>
      <Header />
      <Container maxWidth="md" sx={{ mt: 4 }}>
        <Typography variant="h4" gutterBottom>About Us</Typography>
        <Typography variant="h5" gutterBottom>Our Mission</Typography>
        <Typography variant="body1" paragraph>
          To provide the children of New Delhi’s slums with the tools to escape the cycle of poverty, and expose them to the things that make life more than just survival.
        </Typography>
        <Typography variant="h5" gutterBottom>Our Past</Typography>
        <Typography variant="body1" paragraph>
          We began as a single small community centre. In this modest one room centre based in the Rohini slums, we created a space in which love, respect, clean water, positive role models, and structure would be guaranteed. The original intent was to provide educational support. It seemed simple; if you help a child complete their schooling, they will be employable, independent, and able to provide for their families. Pretty quickly, it became apparent that it takes more than just a lesson plan to prove to a child living in poverty just how bright their future can be. From then our activities began to include dance, yoga, meditation, art, drama, cultural events, health and sanitation camps, and the list kept growing. Prajna began supporting the livelihood not just the education of the children in Delhi’s slums.
        </Typography>
        <Typography variant="h5" gutterBottom>Our Present</Typography>
        <Typography variant="body1" paragraph>
          With six community centres operating all across New Delhi, we have come a long way since our initial one room center. Each centre is equipped with computers and books and staffed with full-time centre directors. Volunteers from every corner of the world, mentors from universities across Delhi, devoted staff mentors, and Delhi slum community members come together inside these spaces to further their education and enrich their lives. We have a grant to fill twelve buses with modern technology to create mobile digital learning labs to serve rural areas just outside of Delhi. In the Kalkaji centre, we have a singer sewing room and certificate-granting instruction to give adolescent girls training for independent income. We have come a long way, but have never lost sight of our original mission. The most important aspect of all the centres is that every child that enters, leaves feeling that they are valued, and that they have something worth living for.
        </Typography>
        <Typography variant="h5" gutterBottom>The People Who Make it Happen</Typography>
        <Grid container spacing={3}>
          <Grid item xs={12} md={6}>
            <Typography variant="h6" gutterBottom>Board of Trustees</Typography>
            <List>
              {boardOfTrustees.map((person, index) => (
                <Card key={index} variant="outlined">
                  <CardContent>
                    <ListItem>
                      <ListItemText primary={person} />
                    </ListItem>
                  </CardContent>
                </Card>
              ))}
            </List>
          </Grid>
          <Grid item xs={12} md={6}>
            <Typography variant="h6" gutterBottom>Partners</Typography>
            <List>
              {partners.map((partner, index) => (
                <Card key={index} variant="outlined">
                  <CardContent>
                    <ListItem>
                      <ListItemText primary={partner} />
                    </ListItem>
                  </CardContent>
                </Card>
              ))}
            </List>
          </Grid>
        </Grid>
      </Container>
    </div>
  );
}

// Define Board of Trustees and Partners
const boardOfTrustees = [
  "Founding Chairperson: Mrs. Veena Sharma",
  "Managing Trustee: Mr. R K Sinha",
  "Trustee: Mr. Jai Rah Singh",
  "Trustee: Mrs. Shashi Bhargava",
  "Trustee: Mr. R C Mody"
];

const partners = [
  "Lady Shri Ram College for Women",
  "Deloitte",
  "Indian Council for Cultural Relations",
  "United Nations"
];

export default AboutUsPage;