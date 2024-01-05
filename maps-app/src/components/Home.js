import React from "react";
import "../styles/Home.css";

export default function Home() {
  return (
    <div className="home-page-wrapper">
      <img
        width="400px"
        src="./images/orienteering.png"
        alt="orienteering"
      ></img>
      <div className="home-page-content-wrapper">
        <h1>If you want to: </h1>
        <ul className="home-page-ul">
          <li className="home-page-li">
            Keep all your orienteering maps in one place.
          </li>
          <li className="home-page-li">
            Find specific maps based on location, year, discipline, age group,
            etc.
          </li>
          <li className="home-page-li">
            Download various orienteering maps for offline use.
          </li>
          <li className="home-page-li">
            Discover remarkable terrains all around the world.
          </li>
          <li className="home-page-li">
            Share your trainings/ competitions with other orienteers.
          </li>
          <li className="home-page-li">
            See how the sport orienteering has evolved over the years.
          </li>
        </ul>
        <h2>That's your place!</h2>
      </div>
    </div>
  );
}
