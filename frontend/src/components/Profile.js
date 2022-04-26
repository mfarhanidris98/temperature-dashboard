import React from "react";
import { Navigate } from 'react-router-dom';
import { useSelector } from "react-redux";
const Profile = () => {
  const { user: currentUser } = useSelector((state) => state.auth);
  if (!currentUser) {
    return <Navigate to="/login" />;
  }
  return (
    <div className="container">
      <header className="jumbotron py-5">
        <h3>
          <strong>{currentUser.username}</strong>'s Profile
        </h3>
      </header>
      <p>
        <strong>Token:</strong> {currentUser.accessToken.substring(0, 20)} ...{" "}
        {currentUser.accessToken.substr(currentUser.accessToken.length - 20)}
      </p>
      <p>
        <strong>Email:</strong> {currentUser.email}
      </p>
      <strong>Authorities:</strong>{currentUser.roles}
    </div>
  );
};
export default Profile;