import { useState, useEffect } from "react";
import "../styles/Filters.css";
import categories from "../constants/categories.js";
import disciplines from "../constants/disciplines.js";
import ageGroups from "../constants/ageGroups.js";
import Slider from "rc-slider";
import "rc-slider/assets/index.css";
import "../styles/Slider.css";

export default function CourseFilters({ handleCourseList }) {
  const [inputs, setInputs] = useState({});
  const [resetFilters, setResetFilters] = useState(false);

  useEffect(() => {
    if (resetFilters) {
      handleCourseList({});
      setResetFilters(false);
    }
  }, [resetFilters, handleCourseList]);

  const handleChange = (event) => {
    const { name, type, value, checked } = event.target;

    if (type === "checkbox") {
      setInputs((prevInputs) => ({ ...prevInputs, [name]: checked }));
    } else {
      setInputs((prevInputs) => ({ ...prevInputs, [name]: value }));
    }

    console.log(inputs);
  };

  function handleDoubleRangeChange(values, minName, maxName) {
    setInputs((prevInputs) => ({
      ...prevInputs,
      [minName]: values[0],
      [maxName]: values[1],
    }));
  }

  function handleReset() {
    setInputs({});
    setResetFilters(true);
  }

  function removeEmptyStringFields(obj) {
    const result = {};
    for (const key in obj) {
      const value = obj[key];
      if (value !== "") {
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
        <select
          id="category"
          name="category"
          value={inputs.category || ""}
          onChange={handleChange}
        >
          <option value="">Select category</option>
          {categories.map((category) => (
            <option key={category} value={category}>
              {category}
            </option>
          ))}
        </select>

        <select
          name="ageGroup"
          id="ageGroup"
          onChange={handleChange}
          value={inputs.ageGroup || ""}
        >
          <option value="">Select age group</option>
          {ageGroups.map((ageGroup) => (
            <option key={ageGroup} value={ageGroup}>
              {ageGroup}
            </option>
          ))}
        </select>

        <select
          name="discipline"
          id="discipline"
          onChange={handleChange}
          value={inputs.discipline || ""}
        >
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
          onChange={(values) =>
            handleDoubleRangeChange(values, "minControls", "maxControls")
          }
        />
        <div className="range-values">
          <span>min: {inputs.minControls || 0} </span>
          <span>max: {inputs.maxControls || 50} </span>
        </div>

        <label htmlFor="distanceRange">Distance:</label>
        <Slider
          range
          min={0}
          max={50}
          value={[inputs.minDistance || 0, inputs.maxDistance || 50]}
          onChange={(values) =>
            handleDoubleRangeChange(values, "minDistance", "maxDistance")
          }
        />
        <div className="range-values">
          <span>min: {inputs.minDistance || 0} </span>
          <span>max: {inputs.maxDistance || 50} </span>
        </div>
        <div className="switch-wrapper">
          <label className="switch">
            <input
              name="isCompetition"
              id="isCompetition"
              type="checkbox"
              onChange={handleChange}
              value={inputs.isCompetition || "false"}
            />
            <span className="slider round"></span>
          </label>
          Competition
        </div>

        <div className="filter-btns-wrapper">
          <button type="submit">Save</button>
          <button className="reset-btn" onClick={handleReset}>
            Reset
          </button>
        </div>
      </form>
    </>
  );
}
