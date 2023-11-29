import React, {useState, useEffect} from "react";
import axios from "axios"; 
import Dropzone from "./Dropzone.js";

export default function AllMaps ({callback}) {
    const [maps, setMaps] = useState([]);
    // const mapsIds=[];

    const fetchAllMaps = () =>{
      axios.get("http://localhost:8080/api/v1/map").then(res => {
        console.log(res);
        setMaps(res.data);
      });
    };
  
    useEffect(() =>{
      fetchAllMaps();
    },[]);
  
    return maps.map((map) => {
        // mapsIds.push(map.id);
      return (
        <div key={map.id}>
          <div className="map-file-container">
          <embed src={`http://localhost:8080/api/v1/map/${map.id}/getMapFile`} type="application/pdf" width="50%" height="300px" />
          </div>
          <br/>
          <br/>
          <h1>{map.city}</h1>
          <p>{map.id}</p>
          <Dropzone id={map.id} /> 
          <br/>
         {/* {callback(mapsIds)} */}
        </div>
      )
    })
  };