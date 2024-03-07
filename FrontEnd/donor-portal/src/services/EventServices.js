import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

const EventServices = {
  getAllEvents: async () => {
    try {
      const response = await axios.get(`${BASE_URL}/events`);
      return response.data;
    } catch (error) {
      console.error('Error fetching events:', error);
      throw error;
    }
  },

  createEvent: async (eventData) => {
    try {
      const response = await axios.post(`${BASE_URL}/events`, eventData);
      return response.data;
    } catch (error) {
      console.error('Error creating event:', error);
      throw error;
    }
  },

  updateEvent: async (eventId, eventData) => {
    try {
      const response = await axios.put(`${BASE_URL}/events/${eventId}`, eventData);
      return response.data;
    } catch (error) {
      console.error('Error updating event:', error);
      throw error;
    }
  },

  deleteEvent: async (eventId) => {
    try {
      const response = await axios.delete(`${BASE_URL}/events/${eventId}`);
      return response.data;
    } catch (error) {
      console.error('Error deleting event:', error);
      throw error;
    }
  }
};

export default EventServices;
