import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

export default function ProtectedRoute({ children }) {
  const navigateTo = useNavigate();
  const { isCustomerAuthenticated } = useAuth();

  const customerAuthenticated = isCustomerAuthenticated();

  // we need to have useEffect because we need to navigate to login on each render
  // and not only when the first time component is rendered
  useEffect(() => {
    if (!customerAuthenticated) {
      navigateTo("/");
    }
  });

  return customerAuthenticated && children;
}
