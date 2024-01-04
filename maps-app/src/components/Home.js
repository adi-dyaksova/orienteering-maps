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
            find a specific map based on country, year or scale or find a course
            filtered by discipline, distance, age group, etc.
          </li>
          <li className="home-page-li">
            save your maps/courses so they are always one click away from you{" "}
          </li>
          <li className="home-page-li">
            discover remarkable terrains all around the world
          </li>
          <li className="home-page-li">
            share your trainings/ competitions with other orienteers
          </li>
          <li className="home-page-li">
            see how the sport orienteering has evolved over the years
          </li>
        </ul>
        <h2>That's your place!</h2>
      </div>
    </div>
  );
}
