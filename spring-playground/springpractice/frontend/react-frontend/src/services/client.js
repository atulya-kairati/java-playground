import axios from "axios";

const BASE_URL = import.meta.env.VITE_API_BASE_URL;

function getAuthHeader(params) {
  const jwt = localStorage.getItem("jwt");

  const headers = {
    Authorization: `Bearer ${jwt}`,
  };

  return headers;
}

export async function getAllCustomers() {
  const res = await axios.get(`${BASE_URL}/api/v1/customers`, {
    headers: getAuthHeader(),
  });

  return res.data;
}

export async function postCustomer(customer) {
  const res = await axios.post(`${BASE_URL}/api/v1/customers`, customer);

  return res.status;
}

/**
 * @param {number} id
 */
export async function deleteCustomerById(id) {
  const res = await axios.delete(`${BASE_URL}/api/v1/customers/${id}`, {
    headers: getAuthHeader(),
  });

  return res.status;
}

export async function updateCustomerById(id, changes) {
  console.log(changes);

  const res = await axios.put(`${BASE_URL}/api/v1/customers/${id}`, changes, {
    headers: getAuthHeader(),
  });
  return res.status;
}

/**
 * @param {string} mail
 * @param {string} password
 * @param {boolean} remember
 */
export async function performLogin(mail, password, remember = true) {
  const data = {
    mail,
    password,
  };

  const res = await axios.post(`${BASE_URL}/api/v1/auth/login`, data);
  return res;
}
