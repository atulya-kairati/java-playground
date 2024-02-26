import axios from "axios";

const BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function getAllCustomers() {
  const res = await axios.get(`${BASE_URL}/api/v1/customers`);

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
  const res = await axios.delete(`${BASE_URL}/api/v1/customers/${id}`);

  return res.status;
}

export async function updateCustomerById(id, changes) {
  console.log(changes);
  const res = await axios.put(`${BASE_URL}/api/v1/customers/${id}`, changes);

  return res.status;
}
