import React, { useState, useEffect } from "react";
import UserService from "../services/user.service";
import DspLineChart from "../views/DspLineChart";
const BoardUser = () => {
  const [content, setContent] = useState("");
  useEffect(() => {
    UserService.getUserBoard().then(
      (response) => {
        setContent(response.data.content);
      },
      (error) => {
        const _content =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();
        setContent(_content);
      }
    );
  }, []);
  return (
    <DspLineChart />
  );
};
export default BoardUser;