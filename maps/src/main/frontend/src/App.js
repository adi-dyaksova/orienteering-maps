import React, { useState } from "react";
import './App.css';
import AllMaps from "./components/AllMaps.js"
import Form from "./components/Form.js";

function App() {
  
  const callFromAllMaps = (mapsIdsArr) => {
    console.log("mapsIds from App");
    console.log(mapsIdsArr);
  }
  return (
    <div>
     
      <div className="form-container">
      <Form/>
      </div>
     <AllMaps callback={callFromAllMaps}/>

    </div>
    
  );
}

export default App;
