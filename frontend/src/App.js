import React, { useState } from 'react';
import './App.css';
import CmpNavbar from './components/CmpNavbar';
import "bootstrap/dist/css/bootstrap.min.css";


function App() {
  return (
    <div>
      <CmpNavbar />
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
