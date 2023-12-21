// CourseList.js
import React, { useState } from "react";
import CourseFilters from "./CourseFilters.js";
import CourseListItem from "./CourseListItem.js";
import { useNavigate } from "react-router-dom";
import SearchBar from "./SearchBar.js";
import axios from "axios";
// import SearchBar from "./SearchBar.js";
import { useEffect } from "react";
import "../styles/MapList.css"

export default function CourseList() {
  const [courses, setCourses] = useState([]);
  // const [showFilters, setShowFilters] = useState(false);
  const navigate = useNavigate();
  

//   function handleAddCourseClick() {
//     // navigate("/addCourse");
//  }
//  function handleShowFilters(){
//   setShowFilters(!showFilters);
//  }

  const fetchAllCourses = () =>{
    console.log("fetch")
    axios.get("http://localhost:8080/api/v1/course").then(res => {
      setCourses(res.data);
    });
  };

  useEffect(() =>{
    fetchAllCourses();
  },[]);

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
  
//     <div className="display-row">
//       <div className="display-col grey-left-border grey-right-border">
//         <div className="filters-btn">
//           <img className="filters-img" src="https://img.icons8.com/ios-glyphs/60/FFFFFF/sorting-options.png" alt="sorting-options"/>
//           Filters 
//         </div>
//         <CourseFilters className="filters" handleCourseList={handleCourseList} />
//       </div>

// <div className=" map-list-wrapper display-col grey-right-border ">
//         {courses.length > 0 ? (
        
//           courses.map((course) => <CourseListItem key={course.courseId} course={course} />) 
//         ) : (
//           <p>No courses match the current filters.</p>
//         )}
// </div>
// </div>
    

<>
<div className="searchbar-addmap-wrapper">
<SearchBar/>
  </div>

{/* <div className="filters-btn-wrapper"> */}
{/* </div>https://icons8.com/icon/69913/slider */}
{/* https://img.icons8.com/ios-filled/100/horizontal-settings-mixer--v1.png */}
 {/* </div> */}
<div className="display-row">
 <div  className="course-page-wrapper filters-background display-col grey-left-border grey-right-border">

   {/* <button className="filters-btn" onClick={handleShowFilters}> */}
   {/* <button className="filters-btn">
     {/* <img className="filters-img" width="20" height="20" src="https://img.icons8.com/ios-filled/100/horizontal-settings-mixer--v1.png" alt="horizontal-settings-mixer--v1"/> }
     <img className="filters-img" src="https://img.icons8.com/ios-glyphs/60/FFFFFF/sorting-options.png" alt="sorting-options"/>
     {/* {showFilters ? 'Hide' : 'Show'} filters  }
     Filters 
   </button> */}

   <div className="filters-title">
     {/* <img className="filters-img" width="20" height="20" src="https://img.icons8.com/ios-filled/100/horizontal-settings-mixer--v1.png" alt="horizontal-settings-mixer--v1"/> */}
     <img className="filters-img" src="./images/filters-icon.png" alt="sorting-options"/>
     {/* {showFilters ? 'Hide' : 'Show'} filters  */}
     Filters 
   </div>

     {/* {showFilters && <Filters className="filters" handleMapList={handleMapList} />} */}
     <CourseFilters className="filters" handleCourseList={handleCourseList} />
 </div>
 
 <div className="map-list-wrapper display-col grey-right-border ">

   {courses.length > 0 ? (
     courses.map((course) => <CourseListItem key={course.courseId} course={course}  />)
   ) : (
     <p className="not-found-msg">No courses match the current filters.</p>
   )}
 </div>
</div>

</>
  );
}
