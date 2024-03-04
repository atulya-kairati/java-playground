import { useContext, createContext, useEffect, useState } from "react";
import { performLogin } from "../../services/client";
import { jwtDecode } from "jwt-decode";

const AuthContext = createContext({});

export default function AuthProvider({ children }) {
  const [customer, setCustomer] = useState(null);

  useEffect(() => {
    const currCustomer = JSON.parse(localStorage.getItem("customer"));

    setCustomer(currCustomer);
  }, []);

  async function login(mail, password) {
    return new Promise((resolve, reject) => {
      performLogin(mail, password)
        .then((res) => {
          const jwt = res.headers.authorization;
          const currCustomer = res.data;

          // save token
          localStorage.setItem("jwt", jwt);
          localStorage.setItem("customer", JSON.stringify(currCustomer));

          setCustomer(currCustomer);
          resolve();
        })
        .catch((err) => reject(err));
    });
  }

  function logOut() {
    localStorage.removeItem("jwt");
    localStorage.removeItem("customer");
    setCustomer(null);
  }

  function isCustomerAuthenticated() {
    const jwt = localStorage.getItem("jwt");

    if (!jwt) {
      return false;
    }

    const { exp: expiration } = jwtDecode(jwt);

    if (expiration < Date.now() / 1000) {
      return false;
    }

    return true;
  }

  return (
    <AuthContext.Provider
      value={{
        customer,
        logOut,
        login,
        isCustomerAuthenticated,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}
