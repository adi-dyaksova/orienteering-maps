
import React, {useState} from "react";
import { Link, useNavigate } from "react-router-dom";
import CroppedPdfImage from "./CroppedPdfImage.js";
import "../styles/MapListItem.css"

export default function MapListItem({map}){
    const navigate = useNavigate();
   // const [moreDetails, setMoreDetails] = useState(false);

    // function handleDescriptionClick(){
    //     setMoreDetails(!moreDetails);
    // }
    // function handleShowFile(event){
    //     window.open(filePath, '_blank');
    // }

    function handleDetailsClick(id){
       navigate(`/details/${id}`);
    }
const filePath=`http://localhost:8080/api/v1/map/${map.id}/getMapFile`;

   return( <>

    <div className="list-item" onClick={() => {handleDetailsClick(map.id)}}>
        <div className="map-item-wrapper">
            {/* <div className="map-file-container" onClick={handleShowFile}> */}
            <div className="map-file-container" >
                {/* <embed src={filePath} type="application/pdf" width="400px" height="300px" /> */}
                {/* <iframe src={filePath} width="50%" height="300px" alt={map.name} /> */}
                <CroppedPdfImage filePath={filePath}/>
            </div>
           <div className="map-content-container ">
                {/* <Link to={`/details/${map.id}`}> */}
               
                    <h2>{map.name}</h2>
                    
                {/* </Link> */}
                <div className="map-content-item">
                {/* <img  width="25" height="25" src="https://img.icons8.com/metro/52/marker.png" alt="location image"/> */}
                {/* <img width="25" height="25" src="https://img.icons8.com/ios/50/marker--v1.png" alt="marker--v1"/> */}
                <img width="28" height="28" src="./images/location.png" alt="visit"/>
                <h3>{map.city}, {map.country}</h3>
                </div>


                <div className="map-content-item">
                <img className="list-icon" width="26" height="26" src="./images/ruler.png" alt="ruler"/>
                <p><span className="bold">Scale: </span> 1:{map.scale}</p>
                </div>

                 <div className="map-content-item">
                <img className="list-icon" width="24" height="24" src="./images/calendar.png" alt="calendar"/>
                <p><span className="bold">Year: </span>{map.year}</p> 
                </div>
       
            </div>

            
            {/* <button className="show-details-btn" onClick={() => {handleDetailsClick(map.id)}}> Details </button> */}
          
        </div>
  
        {/* <button onClick={handleDescriptionClick}>
            {moreDetails ? 'Hide' : 'Show'} description
        </button> */}
        {/* {moreDetails && <p>{map.description}</p> } */}
       {/* <button onClick={handleShowFile}>
           Open Map File
        </button> */}
        
        
        </div>
    </>)
}