
import React, {useState} from "react";
import Dropzone from "./Dropzone.js";
import { Link, useNavigate } from "react-router-dom";
import CroppedPdfImage from "./CroppedPdfImage.js";
import "../styles/MapListItem.css"

export default function CourseListItem({course}){
     const navigate = useNavigate();

    // function handleShowFile(event){
    //     window.open(filePath, '_blank');
    // }

    function handleDetailsClick(id){
       navigate(`/courseDetails/${id}`);
    }
const filePath=`http://localhost:8080/api/v1/course/${course.courseId}/getCourseFile`;

   return( <>
{console.log(course)}
            <div className="list-item" onClick={() => {handleDetailsClick(course.courseId)}}>
            <div className="map-item-wrapper">
            <div className="map-file-container">
                <CroppedPdfImage filePath={filePath} />
            </div>
           <div className="map-content-container">
           
          <h2> {course.category}</h2>
          <h3>{course.discipline}</h3> 

          <div className="map-content-item">
                <img className="list-icon" width="26" height="26" src="../images/agegroup.png" alt="age-group"/>
                <p>Age group: <span className="bold">{course.ageGroup}</span> </p> 
            </div>

            <div className="map-content-item">
                <img className="list-icon" width="26" height="26" src="../images/controls.png" alt="controls"/>
                <p>Controls: <span className="bold">{course.controls}</span></p> 
            </div>
          
            <div className="map-content-item">
                <img className="list-icon" width="26" height="26" src="../images/width.png" alt="width"/>
                <p>Distance <span className="bold">{course.distance} km</span></p>  
            </div>
         
            <div className="map-content-item">
                <img className="list-icon" width="26" height="26" src="../images/trophy.png" alt="trophy"/>
                <p>Competition:  <span className="bold">{course.competition ? 'yes' : 'no'}</span></p>
            </div>
         
           
         
          </div>

          </div>
  
         </div>
    </>)
}