import React from "react";
import { useNavigate } from "react-router-dom";
import CroppedPdfImage from "./CroppedPdfImage.js";
import "../styles/MapListItem.css";

export default function MapListItem({ map }) {
  const navigate = useNavigate();

  function handleDetailsClick(id) {
    navigate(`/details/${id}`);
  }
  const filePath = `http://localhost:8080/api/v1/map/${map.id}/getMapFile`;

  return (
    <>
      <div
        className="list-item"
        onClick={() => {
          handleDetailsClick(map.id);
        }}
      >
        <div className="map-item-wrapper">
          <div className="map-file-container">
            <CroppedPdfImage filePath={filePath} />
          </div>
          <div className="map-content-container ">
            <h2>{map.name}</h2>

            <div className="map-content-item">
              <img
                width="28"
                height="28"
                src="./images/location.png"
                alt="visit"
              />
              <h3>
                {map.city}, {map.country}
              </h3>
            </div>

            <div className="map-content-item">
              <img
                className="list-icon"
                width="26"
                height="26"
                src="./images/ruler.png"
                alt="ruler"
              />
              <p>
                <span className="bold">Scale: </span> 1:{map.scale}
              </p>
            </div>

            <div className="map-content-item">
              <img
                className="list-icon"
                width="24"
                height="24"
                src="./images/calendar.png"
                alt="calendar"
              />
              <p>
                <span className="bold">Year: </span>
                {map.year}
              </p>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
