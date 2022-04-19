import React, { useState } from 'react';
import './App.css';
import CmpNavbar from './components/CmpNavbar';
import { Router, Link, Route, Routes, BrowserRouter } from "react-router-dom";

import DspUploadFile from './views/DspUploadFile';
import CmpUploadFile from './components/CmpUploadFile';


function App() {
  return (
    <div>
      <CmpUploadFile />
    </div>

    // <BrowserRouter>
    //   <div className="container mt-3">
    //     <CmpNavbar />
    //     <Routes>
    //       <Route exact path={["/", "/UploadFile"]} component={CmpUploadFile} />
    //       {/* <Route exact path="/add" component={AddTutorial} />
    //       <Route path="/tutorials/:id" component={Tutorial} /> */}
    //     </Routes>
    //   </div>
    // </BrowserRouter>
  );
}

export default App;
