import axios from 'axios';

// const BASE_URL = 'http://localhost:8080';
const BASE_URL = 'https://volunteerdonorportal-volunteerdonorportal.azuremicroservices.io';

const EventServices = {
  getAllEvents: async () => {
    try {
      const response = await axios.get(`${BASE_URL}/event`);
      return response.data;
    } catch (error) {
      console.error('Error fetching events:', error);
      throw error;
    }
  },

  createEvent: async (eventData) => {
    try {
      const response = await axios.post(`${BASE_URL}/event`, null, {
        params: eventData
      });
      return response.data;
    } catch (error) {
      console.error('Error creating event:', error);
      throw error;
    }
  },

  updateEvent: async (eventId, eventData) => {
    try {
      const response = await axios.put(`${BASE_URL}/event`, null, {
        params: eventData
      });
      return response.data;
    } catch (error) {
      console.error('Error updating event:', error);
      throw error;
    }
  },  

  deleteEvent: async (eventId) => {
    try {
      const response = await axios.delete(`${BASE_URL}/event`, {
        params: {
          eventId: eventId
        }
      });
      return response.data;
    } catch (error) {
      console.error('Error deleting event:', error);
      throw error;
    }
  }
  
};

export default EventServices;
