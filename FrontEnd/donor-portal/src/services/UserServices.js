import axios from 'axios';

const BASE_URL = 'http://localhost:8080';
// const BASE_URL = 'https://volunteerdonorportal-volunteerdonorportal.azuremicroservices.io';

export const registerUser = async (userData) => {
  try {
    const response = await axios.post(`${BASE_URL}/user`, null, {
      params: userData
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const loginUser = async (email, password) => {
  try {
    const response = await axios.get(`${BASE_URL}/user/login`, {
      params: {
        email: email,
        password: password
      }
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};
