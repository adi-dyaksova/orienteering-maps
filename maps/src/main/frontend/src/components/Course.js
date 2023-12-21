import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";
import PdfImageDisplay from "./PdfImageDisplay.js";
import "../styles/Map.css"
export default function Course(){
    const {id} = useParams();
    const [course, setCourse] = useState({});
    const filePath=`http://localhost:8080/api/v1/course/${id}/getCourseFile`;

    const fetchCourse = () =>{
      axios.get(`http://localhost:8080/api/v1/course/${id}`).then(res => {
        setCourse(res.data);
      });
    };
  
    useEffect(() => {
        fetchCourse();
    },[]);
  

    function handleShowFile(event){
      window.open(filePath, '_blank');
  }
  
    return (

<div className="course-page display-row">
      <div >
        {/* <h1>{course.category}</h1>
       <p>{course.ageGroup}</p>
       <p>{course.discipline}</p>
       <p>Number of controls: {course.controls}</p>
       <p>Distance: {course.distance}</p>
       <p>Competition: {course.competition ? "yes": "no"}</p> */}
        <h1> {course.category}</h1>
        <h3>{course.discipline}</h3> 
        <p>Age group: <span className="bold">{course.ageGroup}</span> </p> 
        <p>Controls: <span className="bold">{course.controls}</span></p> 
        <p>Distance <span className="bold">{course.distance} km</span></p>  
        <p>Competition:  <span className="bold">{course.competition ? 'yes' : 'no'}</span></p> 
        <button onClick={handleShowFile}>Download</button>
      </div>

      <div className="pdf-image-course">
      {/* <embed src={filePath} width="100%" height="100%"/> */}
      <PdfImageDisplay pdfUrl={filePath}/>
      </div>
      </div>
       
      )

}