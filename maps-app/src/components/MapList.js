import React, { useState } from "react";
import Filters from "./Filters.js";
import MapListItem from "./MapListItem.js";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import SearchBar from "./SearchBar.js";
import { useEffect } from "react";
import "../styles/MapList.css";

export default function MapList() {
  const [maps, setMaps] = useState([]);
  const navigate = useNavigate();

  function handleAddMapClick() {
    navigate("/addMap");
  }

  const fetchAllMaps = () => {
    console.log("fetch");
    axios.get("http://localhost:8080/api/v1/map").then((res) => {
      setMaps(res.data);
    });
  };

  useEffect(() => {
    fetchAllMaps();
  }, []);

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

  function handleSearch(searchTerm) {
    console.log("handle searchTerm");
    console.log(searchTerm);
    axios
      .post(`http://localhost:8080/api/v1/map/search`, searchTerm)
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
        <SearchBar handleSearch={handleSearch} />
        <button className="add-map-btn" onClick={handleAddMapClick}>
          {" "}
          Add map{" "}
        </button>
      </div>

      <div className="display-row">
        <div className="filters-background display-col grey-left-border grey-right-border">
          <div className="filters-title">
            <img
              className="filters-img"
              src="./images/filters-icon.png"
              alt="sorting-options"
            />
            Filters
          </div>
          <Filters className="filters" handleMapList={handleMapList} />
        </div>

        <div className="map-list-wrapper display-col grey-right-border ">
          {maps.length > 0 ? (
            maps.map((map) => <MapListItem key={map.id} map={map} />)
          ) : (
            <p className="not-found-msg">No maps match the current filters.</p>
          )}
        </div>
      </div>
    </>
  );
}
