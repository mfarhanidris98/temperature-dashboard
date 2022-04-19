import React from 'react';
import { Router, Link, Route } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "../App.css";
import DspUploadFile from '../views/DspUploadFile';
import CmpUploadFile from './CmpUploadFile';

function CmpNavbar() {
  return (
    <div>
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <a href="/tutorials" className="navbar-brand">
          TemperDash
        </a>
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/UploadFile"} className="nav-link">
              Upload
            </Link>
          </li>
          {/* <li className="nav-item">
            <Link to={"/add"} className="nav-link">
              Add
            </Link>
          </li> */}
        </div>
      </nav>

    </div>
  );
}

export default CmpNavbar;