// MapList.js
import React, { useState } from "react";
import Filters from "./Filters.js";
import MapListItem from "./MapListItem.js";
import { useNavigate } from "react-router-dom";
import axios from "axios";
// import { SearchBar } from '@rneui/themed';
import SearchBar from "./SearchBar.js";
import { useEffect } from "react";
import "../styles/MapList.css"

export default function MapList() {
  const [maps, setMaps] = useState([]);
  // const [showFilters, setShowFilters] = useState(false);
  // const [inputs, setInputs] = useState({});
  // const [resetFilters,setResetFilters]=useState(false);
  const navigate = useNavigate();
  

  function handleAddMapClick() {
    navigate("/addMap");
 }
//  function handleShowFilters(){
//   setShowFilters(!showFilters);
//  }

  const fetchAllMaps = () =>{
    console.log("fetch")
    axios.get("http://localhost:8080/api/v1/map").then(res => {
      setMaps(res.data);
    });
  };

  useEffect(() =>{
    fetchAllMaps();
  },[]);

  function handleMapList(inputs) {
    console.log("handle map list");
    console.log(inputs);
    
        axios
          .post(`http://localhost:8080/api/v1/map/filter`, inputs)
          .then((res) => {
            console.log("maps filtered successfully", res.data);
            setMaps(res.data);
          })
          .catch((err) => {
            console.log(err);
          });

  }

  function handleSearch(searchTerm){ //TODO: reset button as well
    console.log("handle searchTerm");
    console.log(searchTerm);
    
        axios
          .post(`http://localhost:8080/api/v1/map/search`, searchTerm) // TODO endpoint
          .then((res) => {
            console.log("map searched successfully", res.data);
            setMaps(res.data);
          })
          .catch((err) => {
            console.log(err);
          });
  }


  return (
    <>
     <div className="searchbar-addmap-wrapper">
    <SearchBar handleSearch={handleSearch}/>
    <button className="add-map-btn" onClick={handleAddMapClick}> Add map </button>
    </div>
 
   {/* <div className="filters-btn-wrapper"> */}
    {/* </div>https://icons8.com/icon/69913/slider */}
    {/* https://img.icons8.com/ios-filled/100/horizontal-settings-mixer--v1.png */}
      {/* </div> */}
    <div className="display-row">
      <div className="filters-background display-col grey-left-border grey-right-border">

        {/* <button className="filters-btn" onClick={handleShowFilters}> */}
        {/* <button className="filters-btn">
          {/* <img className="filters-img" width="20" height="20" src="https://img.icons8.com/ios-filled/100/horizontal-settings-mixer--v1.png" alt="horizontal-settings-mixer--v1"/> }
          <img className="filters-img" src="https://img.icons8.com/ios-glyphs/60/FFFFFF/sorting-options.png" alt="sorting-options"/>
          {/* {showFilters ? 'Hide' : 'Show'} filters  }
          Filters 
        </button> */}

        <div className="filters-title">
          {/* <img className="filters-img" width="20" height="20" src="https://img.icons8.com/ios-filled/100/horizontal-settings-mixer--v1.png" alt="horizontal-settings-mixer--v1"/> */}
          {/* <img className="filters-img" src="https://img.icons8.com/ios-glyphs/60/FFFFFF/sorting-options.png" alt="sorting-options"/> */}
          <img className="filters-img" src="./images/filters-icon.png" alt="sorting-options"/>
          {/* {showFilters ? 'Hide' : 'Show'} filters  */}
          Filters 
        </div>

          {/* {showFilters && <Filters className="filters" handleMapList={handleMapList} />} */}
          <Filters className="filters" handleMapList={handleMapList} />
      </div>
      
      <div className="map-list-wrapper display-col grey-right-border ">

        {maps.length > 0 ? (
          maps.map((map) => <MapListItem key={map.id} map={map}  />)
        ) : (
          <p className="not-found-msg">No maps match the current filters.</p>
        )}
      </div>
    </div>

    </>
  );
}
