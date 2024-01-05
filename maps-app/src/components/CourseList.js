import React, { useState } from "react";
import CourseFilters from "./CourseFilters.js";
import CourseListItem from "./CourseListItem.js";
import axios from "axios";
import { useEffect } from "react";
import "../styles/MapList.css";

export default function CourseList() {
  const [courses, setCourses] = useState([]);

  const fetchAllCourses = () => {
    console.log("fetch");
    axios.get("http://localhost:8080/api/v1/course").then((res) => {
      setCourses(res.data);
    });
  };

  useEffect(() => {
    fetchAllCourses();
  }, []);

  function handleCourseList(inputs) {
    console.log("handle course list");
    console.log(inputs);

    axios
      .post(`http://localhost:8080/api/v1/course/filter`, inputs)
      .then((res) => {
        console.log("courses filtered successfully", res.data);
        setCourses(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }

  return (
    <>
      <div className="searchbar-addmap-wrapper"></div>

      <div className="display-row">
        <div className="course-page-wrapper filters-background display-col grey-left-border grey-right-border">
          <div className="filters-title">
            <img
              className="filters-img"
              src="./images/filters-icon.png"
              alt="sorting-options"
            />
            Filters
          </div>

          <CourseFilters
            className="filters"
            handleCourseList={handleCourseList}
          />
        </div>

        <div className="map-list-wrapper display-col grey-right-border ">
          {courses.length > 0 ? (
            courses.map((course) => (
              <CourseListItem key={course.courseId} course={course} />
            ))
          ) : (
            <p className="not-found-msg">
              No courses match the current filters.
            </p>
          )}
        </div>
      </div>
    </>
  );
}
