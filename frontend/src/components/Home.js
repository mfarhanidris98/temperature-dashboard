import React, { useState, useEffect } from "react";
import UserService from "../services/user.service";
const Home = () => {
  const [content, setContent] = useState("");
  useEffect(() => {
    UserService.getPublicContent().then(
      (response) => {
        setContent(response.data.content);
      },
      (error) => {
        const _content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
        setContent(_content);
      }
    )
      .catch(error => console.log('There was an error:' + error));
  }, []);
  return (
    <div className="container">
      <header className="jumbotron">
        <h3>{content}</h3>
        <h3>This is home</h3>
      </header>
    </div>
  );
};
export default Home;