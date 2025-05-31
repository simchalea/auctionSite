import { Navigate } from "react-router-dom";
// import { isLoggedIn } from "../Auth";

const PrivateRoute = ({ children }) => {
  const user = JSON.parse(localStorage.getItem("user"));

  if (!user) {
    return <Navigate to="/" />;
  }

  return children;
};

export default PrivateRoute;
