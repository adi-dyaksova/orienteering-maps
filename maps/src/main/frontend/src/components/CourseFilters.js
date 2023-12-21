import { useState, useEffect } from "react";
import "../styles/Filters.css";
import categories from "../categories.js";
import disciplines from "../disciplines.js";
import ageGroups from "../ageGroups.js";
import Slider from "rc-slider";
import "rc-slider/assets/index.css";
import '../styles/Slider.css';


export default function CourseFilters({ handleCourseList }) {
  const [inputs, setInputs] = useState({});
  const [resetFilters, setResetFilters] = useState(false);


  useEffect(() => {
    if (resetFilters) {
        handleCourseList({});
      setResetFilters(false); // Reset the flag after handling the reset
    }
  }, [resetFilters, handleCourseList]);

  const handleChange = (event) => {
    const { name, type, value, checked } = event.target;
  
    if (type === 'checkbox') {
      setInputs((prevInputs) => ({ ...prevInputs, [name]: checked }));
    }
    //  else if (type === 'range') {
    //   // Handle the slider change
    //   setInputs((prevInputs) => ({
    //     ...prevInputs,
    //     minControls: event[0],
    //     maxControls: event[1],
    //   }));
    // } 
    else {
      setInputs((prevInputs) => ({ ...prevInputs, [name]: value }));
    }
  
    console.log(inputs);
  };

  function handleDoubleRangeChange(values, minName, maxName){
    setInputs((prevInputs) => ({
      ...prevInputs,
      [minName]: values[0],
      [maxName]: values[1],
    }));
  }
  
//   const handleChange = (event) => {
//     const { name, value, type, checked } = event.target;
  
//     // Handle the competition checkbox separately
//     if (type === "checkbox" && name === "isCompetition") {
//       setInputs((values) => ({ ...values, [name]: checked ? "true" : "false" }));
//     } else {
//       setInputs((values) => ({ ...values, [name]: value }));
//     };
//   };
 

  function handleReset() {
    setInputs({});
    setResetFilters(true); // Set the flag to indicate reset
  }

  function removeEmptyStringFields(obj) {
    const result = {};
    for (const key in obj) {
      const value = obj[key];
      if (value !== '') {
        result[key] = value;
      }
    }
    return result;
  }


  function handleSubmit(event) {
    event.preventDefault();
    const finalInputs = removeEmptyStringFields(inputs);
    setInputs(finalInputs);
    handleCourseList(finalInputs);
    console.log("final filter inputs", inputs);
  }

  return (
    <>
      <form className="filters-form" onSubmit={handleSubmit}>
        <select id="category" name="category" value={inputs.category || ''} onChange={handleChange}>
              <option value="">Select category</option>
              {categories.map((category) => (
                <option key={category} value={category}>
                  {category}
                </option>
              ))}
            </select>


        <select name="ageGroup" id="ageGroup" onChange={handleChange} value={inputs.ageGroup || ""}>
          <option value="">Select age group</option>
          {ageGroups.map((ageGroup) => (
                <option key={ageGroup} value={ageGroup}>
                  {ageGroup}
                </option>
              ))}
        </select>

        <select name="discipline" id="discipline" onChange={handleChange} value={inputs.discipline || ""}>
          <option value="">Select discipline</option>
          {disciplines.map((discipline) => (
                <option key={discipline} value={discipline}>
                  {discipline}
                </option>
              ))}
        </select>


        <label htmlFor="controlsRange">Controls:</label>
        <Slider
  range
  min={0}
  max={50}
  value={[inputs.minControls || 0, inputs.maxControls || 50]}
  onChange={(values)=>handleDoubleRangeChange(values,"minControls","maxControls")}
/>
        <div className="range-values">
          <span>min: {inputs.minControls || 0} </span>
          <span>max: {inputs.maxControls || 50} </span>
        </div>
       
        {/* <input type="number" id="minControls" name="minControls" placeholder="Min controls" value={inputs.minControls || ''} onChange={handleChange}></input>
        <input type="number" id="maxControls" name="maxControls" placeholder="Max controls" value={inputs.maxControls || ''} onChange={handleChange}></input> */}
        {/* TODO: text to double */}


        <label htmlFor="distanceRange">Distance:</label>
        <Slider
  range
  min={0}
  max={50}
  value={[inputs.minDistance || 0, inputs.maxDistance || 50]}
  onChange={(values)=>handleDoubleRangeChange(values,"minDistance","maxDistance")}
/>
        <div className="range-values">
          <span>min: {inputs.minDistance || 0} </span>
          <span>max: {inputs.maxDistance || 50} </span>
        </div>
        {/* <input type="number" id="minDistance" name="minDistance" placeholder="Min Distance (km)" value={inputs.minDistance || ''} onChange={handleChange}></input>
        <input type="number" id="maxDistance" name="maxDistance" placeholder="Max Distance (km)" value={inputs.maxDistance || ''} onChange={handleChange}></input>
         */}

{/* <select name="isCompetition" id="isCompetition" onChange={handleChange} value={inputs.isCompetition || ""}>
          <option value="">Is competition?</option>
         <option value="true">Competition</option>
         <option value="false">Training</option>
        </select> */}
                <div className="switch-wrapper">
            <label className="switch">
              <input name="isCompetition" id="isCompetition" type="checkbox" onChange={handleChange} value={inputs.isCompetition || "false"}/>
              <span className="slider round"></span>
            </label>
            Competition
          </div>

        <div className="filter-btns-wrapper">
          <button type="submit">Save</button>
          <button className="reset-btn" onClick={handleReset}>Reset</button>
        </div>
      </form>
    </>
  );
}
