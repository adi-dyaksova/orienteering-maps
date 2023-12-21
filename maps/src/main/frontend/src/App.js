import React from "react";
import './App.css';
import MapList from "./components/MapList.js";
import AddMapForm from "./components/AddMapForm.js";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navigation from "./components/Navigation.js";
import Map from "./components/Map.js";
import CourseList from "./components/CourseList.js";
import Course from "./components/Course.js";
import AddCourseForm from "./components/AddCourseForm.js";
import Home from "./components/Home.js";

function App() {
  return (
    <Router>
      <Navigation />
      <Routes>
        <Route path="home" element={<Home />} />
        <Route
          index
          element={<Home />}
        />
        <Route path="maps" element={<MapList />} />
        <Route path="courses" element={<CourseList />} />
        <Route path="addMap" element={<AddMapForm />} />
        <Route path="addCourse/:id" element={<AddCourseForm />} />
        <Route path="details/:id" element={<Map />} />
        <Route path="courseDetails/:id" element={<Course />} />
      </Routes>
    </Router>
  );
}

export default App;
