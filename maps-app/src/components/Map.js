import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";
import PdfImageDisplay from "./PdfImageDisplay.js";
import "../styles/Map.css";
import CourseListItem from "./CourseListItem.js";
import { useNavigate } from "react-router-dom";
export default function Map() {
  const { id } = useParams();
  const [map, setMap] = useState({});
  const [courses, setCourses] = useState([]);
  const navigate = useNavigate();

  function handleAddCourse(mapId) {
    navigate(`/addCourse/${mapId}`);
  }
  const filePath = `http://localhost:8080/api/v1/map/${id}/getMapFile`;

  const fetchMap = () => {
    axios
      .get(`http://localhost:8080/api/v1/map/${id}`)
      .then((res) => {
        setMap(res.data);
      })
      .catch((err) => {
        console.log("fetch map error: ");
        console.log(err);
      });
  };

  const fetchCourses = () => {
    axios
      .get(`http://localhost:8080/api/v1/course/filter/${id}`)
      .then((res) => {
        setCourses(res.data);
      })
      .catch((err) => {
        console.log("fetch courses of map error");
        console.log(err);
      });
  };

  useEffect(() => {
    fetchMap();
    fetchCourses();
  }, []);

  function handleShowFile(event) {
    window.open(filePath, "_blank");
  }

  return (
    <>
      <div className="map-page-content-btns-wrapper">
        <div className="details-container">
          <h1>{map.name}</h1>
          <h3>
            {map.city}, {map.country}
          </h3>
          <p>
            <span className="bold">Scale: </span> 1:{map.scale}
          </p>
          <p>
            <span className="bold">Year: </span>
            {map.year}
          </p>
          <p>{map.description}</p>
          <button onClick={handleShowFile}>Download</button>
          <PdfImageDisplay pdfUrl={filePath} />
        </div>

        <div className="map-courses-list-wrapper display-col">
          <div className="map-page-btns-wrapper">
            <h2 className="courses-title">Courses</h2>
            <button
              onClick={() => {
                handleAddCourse(map.id);
              }}
            >
              Add Course
            </button>
          </div>

          <div className="scrollable-container display-col grey-right-border grey-left-border ">
            {courses.length > 0 ? (
              courses.map((course) => (
                <CourseListItem key={course.courseId} course={course} />
              ))
            ) : (
              <p className="not-found-msg">
                No courses are available for this map.
              </p>
            )}
          </div>
        </div>
      </div>
    </>
  );
}
