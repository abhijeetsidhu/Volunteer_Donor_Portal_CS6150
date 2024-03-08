import React from 'react';
import Header from './Header';
import { Container, Typography, Grid } from '@mui/material';
import Carousel from 'react-bootstrap/Carousel';
import CountUp from 'react-countup';
import FoundationLogo from '../logos/Prajna-Logo.jpg';

function MainPage() {
  return (
    <div>
      <Header />
      <Container maxWidth="lg">
        {/* Title Section */}
        <section style={{ textAlign: 'center', paddingTop: '20px', display: 'flex', alignItems: 'center' }}>
          <img src={FoundationLogo} alt="Foundation Logo" style={{ width: '250px', marginRight: '10px' }} /> {/* Add your foundation logo here */}
          <div>
            <Typography variant="h3" gutterBottom style={{ fontSize: '3rem', fontWeight: 'bold' }}>
              Building a Safer &amp; Happier World
            </Typography>
            <Typography variant="h4" gutterBottom style={{ fontSize: '2rem' }}>
              Support 5000 Poor People – Save Lives!
            </Typography>
          </div>
        </section>

        {/* Carousel Section */}
        <section>
          <Carousel>
            <Carousel.Item interval={3000}>
              <img
                className="d-block w-100"
                src="https://freevolunteering.net/wp-content/uploads/2016/05/FV-image-1.jpg"
                alt="One" 
              />
            </Carousel.Item>
            <Carousel.Item interval={3000}>
              <img
                className="d-block w-100"
                src="https://images.indianexpress.com/2020/04/food-4.jpg?w=414"
                alt="two" 
              />
            </Carousel.Item>
            <Carousel.Item interval={3000}>
              <img
                className="d-block w-100"
                src="https://devxchange.org/wp-content/uploads/2019/09/devx2-1-1-768x576-768x422.jpg"
                alt="three" 
              />
            </Carousel.Item>
          </Carousel>
        </section>

        <section style={{ paddingTop: '30px', paddingBottom: '30px' }}>
          <Typography variant="h6" gutterBottom style={{ fontSize: '1.8rem' }}>
            For 26 years, the Prajna Foundation has been providing the children of New Delhi’s slums with the tools to escape the cycle of poverty and expose them to the things that make life more than just survival. More than 50% of Delhi’s children are residents of slum neighborhoods. Our mission has only just begun.
          </Typography>
        </section>

        {/* Introduction Section */}
        <section style={{ paddingTop: '30px', paddingBottom: '30px' }}>
          <Grid container spacing={3}>
            <Grid item xs={12} md={6} style={{ display: 'flex', flexDirection: 'column', justifyContent: 'center' }}>
              <Typography variant="body1" gutterBottom style={{ fontSize: '2.8rem' }}>
                <CountUp start={0} end={6} duration={2} separator="," /> Centres and Counting
              </Typography>
              <br/>
              <Typography variant="body1" gutterBottom style={{ fontSize: '2.8rem' }}>
                <CountUp start={0} end={24} duration={2} separator="," /> Proud Years of Service
              </Typography>
              <br/>
              <Typography variant="body1" gutterBottom style={{ fontSize: '2.8rem' }}>
                <CountUp start={0} end={1800000} duration={2} separator="," /> Children Living in the Slums
              </Typography>
            </Grid>
            <Grid item xs={12} md={6}>
              <Carousel>
                <Carousel.Item interval={3000}>
                  <img
                    className="d-block w-100"
                    src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7LhIFqAu9CInJ_ZsOxF5ThkMPB_uwtZsy_EyHGX9GvtG9EdI1UMX-zLqLRWt4ITPCC2w&usqp=CAU"
                    alt="One" 
                    style={{ maxWidth: '100%', height: 'auto', maxHeight: '600px' }}
                  />
                </Carousel.Item>
                <Carousel.Item interval={3000}>
                  <img
                    className="d-block w-100"
                    src="https://pbs.twimg.com/media/FfwMxULakAATadE.jpg"
                    alt="two" 
                    style={{ maxWidth: '100%', height: 'auto', maxHeight: '600px' }}
                  />
                </Carousel.Item>
                <Carousel.Item interval={3000}>
                  <img
                    className="d-block w-100"
                    src="https://prajnafoundation.in/wp-content/uploads/bfi_thumb/DSC_0037-oevd12rh58c695mfom3imr8x4odvzjor62axsw71kg.jpg"
                    alt="three" 
                    style={{ maxWidth: '100%', height: 'auto', maxHeight: '600px' }}
                  />
                </Carousel.Item>
                <Carousel.Item interval={3000}>
                  <img
                    className="d-block w-100"
                    src="https://pbs.twimg.com/media/FmmNa4eacAUwgQc.jpg"
                    alt="four" 
                    style={{ maxWidth: '100%', height: 'auto', maxHeight: '600px' }}
                  />
                </Carousel.Item>
              </Carousel>
            </Grid>
          </Grid>
        </section>

        {/* Slum Statistics Section */}
        <section style={{ paddingTop: '30px', paddingBottom: '30px' }}>
          <Typography variant="h6" gutterBottom style={{ fontSize: '1.8rem' }}>
            According to the India Directorate of Economics and Statistics, a slum is defined as “a compact settlement of at least 20 households with a collection of poorly built tenements, mostly of temporary nature, crowded together usually with inadequate sanitary and drinking water facilities in unhygienic conditions.” Only 33% of slums have any latrine system at all, less than 30% had any local garbage pick-up, and 86.5% of slum areas use tap or hand pump as the primary source of drinking water. Only 4% of the slums have any kind of organization (formal or informal) working for the improvement of life conditions. 52% of Delhi’s population lives in the slums. That is 52% of Delhi’s future. Let's create a generation that changes these statistics.
          </Typography>
          {/* Add images here to illustrate the statistics */}
        </section>
      </Container>
    </div>
  );
}

export default MainPage;