import axios from 'axios';
const API_URL = "http://localhost:8080/api/auth";
const activation = (email, username) => {
  username = email;
  return axios.post("http://localhost:8080/api/activation/sendEmail", { username });
};
const register = (username, email, password) => {
  username = email;
  return axios.post("http://localhost:8080/api/register/signup", {
    username,
    email,
    password,
  });
};
const login = (username, password) => {
  return axios
    .post(API_URL + "/login", {
      username,
      password,
    })
    .then((response) => {
      if (response.data.accessToken) {
        localStorage.setItem("user", JSON.stringify(response.data));
      }
      return response.data;
    });
};
const logout = () => {
  localStorage.removeItem("user");
};
export default {
  activation,
  register,
  login,
  logout,
};