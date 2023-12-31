import React, {useState, useEffect} from "react";
import axios from "axios"; 
import { useNavigate } from "react-router-dom";
import {useDropzone} from 'react-dropzone';
import { useParams } from "react-router-dom";

import categories from "../categories.js";
import disciplines from "../disciplines.js";
import ageGroups from "../ageGroups.js";

import Alert from '@mui/material/Alert';
import AlertTitle from '@mui/material/AlertTitle';
import '../styles/AddForm.css'; 

export default function CourseForm (){
  const {id} = useParams();
    const [inputs, setInputs] = useState({mapId:id});
    
    const [file,setFile] =useState(); //initial value=??
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const navigate = useNavigate();
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
  
      // axios.post(`http://localhost:8080/api/v1/course/insertCourseAndUploadFile`,
      // formData,
      // {
      //   headers: {
      //     "Content-Type": "multipart/form-data"
      //   }
      // }
      // ).then (() => {
      //   console.log("course added and file uploaded successfully")
      // }).catch( err => {
      //   console.log(err);
      // });

      try {
        // Make a POST request to your server endpoint with the form data
        const response = await axios.post(`http://localhost:8080/api/v1/course/insertCourseAndUploadFile`,
         formData,      {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        });
  
        // Check the response status and update the state accordingly
        if (response.status === 200) {
          setSuccessMessage("Course added and file uploaded successfully!");
          setErrorMessage(""); // Reset error message
        } else {
          setSuccessMessage(""); // Reset success message
          setErrorMessage("Error: Course not added or file not uploaded.");
        }
      } catch (error) {
        console.error("Error:", error);
        setSuccessMessage(""); // Reset success message
        setErrorMessage("Map not added or file not uploaded.");
      }

     }

     function handleClose(){
      setErrorMessage("");
      setSuccessMessage("");
      navigate(`/addCourse/${id}`);
    }

    return (
      <>
      <p className="add-form-title">Add Course Form</p>
      { !successMessage && !errorMessage && <form className="add-form" onSubmit={handleSubmit}>
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
          </form>}

          {successMessage && <Alert className="alert" severity="success" onClose={handleClose}>
        <AlertTitle>Success</AlertTitle>
        {successMessage}
      </Alert>}

     { errorMessage && <Alert className="alert" severity="error" onClose={handleClose}>
        <AlertTitle>Error</AlertTitle>
        {errorMessage}
      </Alert>}
          </>
    )
  }