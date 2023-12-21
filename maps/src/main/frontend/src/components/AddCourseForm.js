import React, {useState, useEffect} from "react";
import axios from "axios"; 
import {useDropzone} from 'react-dropzone';
import { useParams } from "react-router-dom";

import categories from "../categories.js";
import disciplines from "../disciplines.js";
import ageGroups from "../ageGroups.js";
import '../styles/AddForm.css'; 

export default function CourseForm (){
  const {id} = useParams();
    const [inputs, setInputs] = useState({mapId:id});
    
    const [file,setFile] =useState(); //initial value=??
    const onDrop = (acceptedFiles) => { 
      console.log(acceptedFiles);
      setFile(acceptedFiles[0]);
     };

    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop});

    // const years = Array.from(
    //   { length: 123 },
    //   (_, index) => new Date().getFullYear() - index
    // );
  
    // const allCountries=countries.map(country => country.name);
    // const scales = [4000,5000,7500,10000,15000];
    
  


    const handleChange = (event) => {
      const name = event.target.name;
      const value = event.target.value;
      setInputs(values => ({...values, [name]: value}))
    }

    const handleSubmit = async (event) => {
      event.preventDefault();
    console.log("final inputs", inputs);
      const json = JSON.stringify(inputs);
      const blob = new Blob([json], {
        type: 'application/json',
      });
    
      const formData = new FormData();
      formData.append("file", file);
      formData.append("course", blob);
  
      axios.post(`http://localhost:8080/api/v1/course/insertCourseAndUploadFile`,
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data"
        }
      }
      ).then (() => {
        console.log("course added and file uploaded successfully")
      }).catch( err => {
        console.log(err);
      });

     }

  
    return (
      <>
      <p className="add-form-title">Add Course Form</p>
      <form className="add-form" onSubmit={handleSubmit}>
           <select id="category" name="category" value={inputs.category || ''} onChange={handleChange}>
              <option value="">Select category</option>
              {categories.map((category) => (
                <option key={category} value={category}>
                  {category}
                </option>
              ))}
            </select>


        <select name="ageGroup" id="ageGroup" onChange={handleChange} value={inputs.ageGroup || "NONE"}>
          <option value="NONE">Select Age Group</option>
          {ageGroups.map((ageGroup) => (
                <option key={ageGroup} value={ageGroup}>
                  {ageGroup}
                </option>
              ))}
        </select>

        <select name="discipline" id="discipline" onChange={handleChange} value={inputs.discipline || "NONE"}>
          <option value="NONE">Select discipline</option>
          {disciplines.map((discipline) => (
                <option key={discipline} value={discipline}>
                  {discipline}
                </option>
              ))}
        </select>


        <input type="number" id="controls" name="controls" placeholder="Number of controls" value={inputs.controls || ''} onChange={handleChange}></input>
        {/* TODO: text to double */}
        <input type="number" id="distance" name="distance" placeholder="Distance" value={inputs.distance || ''} onChange={handleChange}></input>
        
        {/* <label>
        Is competition?
        <input type="checkbox" name="competiton" value="true" /> 
      </label> */}

<select name="competition" id="competition" onChange={handleChange} value={inputs.competition || ""}>
          <option value="">Is competition?</option>
         <option value="true">Competition</option>
         <option value="false">Training</option>
        </select>

          <div {...getRootProps()}>
        <input {...getInputProps()} />
        {
          isDragActive ?
            <p>Drop the files here ...</p> :
            <p>Drag 'n' drop some files here, or click to select files</p>
        }
      </div>
          <button type="submit" >Submit</button>
          </form>
          </>
    )
  }