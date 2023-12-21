import React, {useState} from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios"; 
import countries from "../countries"
import {useDropzone} from 'react-dropzone';
import Alert from '@mui/material/Alert';
import AlertTitle from '@mui/material/AlertTitle';
import '../styles/AddForm.css'; 
export default function Form (){
    const [inputs, setInputs] = useState({});
    const [file,setFile] =useState(); //initial value=??
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const navigate = useNavigate();

    const onDrop = (acceptedFiles) => { 
      console.log(acceptedFiles);
      setFile(acceptedFiles[0]);
     };

    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop});

    const years = Array.from(
      { length: 123 },
      (_, index) => new Date().getFullYear() - index
    );
  
    const allCountries=countries.map(country => country.name);
    const scales = [4000,5000,7500,10000,15000];
  


    const handleChange = (event) => {
      const name = event.target.name;
      const value = event.target.value;
      setInputs(values => ({...values, [name]: value}))
    }

    const handleSubmit = async (event) => {
      event.preventDefault();
    
      const json = JSON.stringify(inputs);
      const blob = new Blob([json], {
        type: 'application/json',
      });
    
      const formData = new FormData();
      formData.append("file", file);
      formData.append("map", blob);
  
      // axios.post(`http://localhost:8080/api/v1/map/insertMapAndUploadFile`,
      // formData,
      // {
      //   headers: {
      //     "Content-Type": "multipart/form-data"
      //   }
      // }
      // ).then (() => {
      //   console.log("map added and file uploaded successfully")
      // }).catch( err => {
      //   console.log(err);
      // });


      try {
        // Make a POST request to your server endpoint with the form data
        const response = await axios.post(`http://localhost:8080/api/v1/map/insertMapAndUploadFile`,
         formData,      {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        });
  
        // Check the response status and update the state accordingly
        if (response.status === 200) {
          setSuccessMessage("Map added and file uploaded successfully!");
          setErrorMessage(""); // Reset error message
        } else {
          setSuccessMessage(""); // Reset success message
          setErrorMessage("Error: Map not added or file not uploaded.");
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
    navigate("/addMap");
  }
    return (
      <>
      <p className="add-form-title">Add Map Form</p>
      { !successMessage && !errorMessage && <form className="add-form" onSubmit={handleSubmit}>
           <input type="text" name="name" id="name" placeholder="Name" value={inputs.name || ''} onChange={handleChange} required />
        {/* <input type="number" id="year" name="year" placeholder="Year" min="1900" max="2030" step="1" value={inputs.year || ''} onChange={handleChange}></input> */}
        <select id="year" name="year" value={inputs.year || ''} onChange={handleChange} required>
              <option value="">Select year</option>
              {years.map((year) => (
                <option key={year} value={year}>
                  {year}
                </option>
              ))}
            </select>
        <input type="text" name="city" id="city" placeholder="City" value={inputs.city || ''} onChange={handleChange} required />
        <select name="country" id="country" onChange={handleChange} value={inputs.country || ""} required>
          <option value="">Select country</option>
          {allCountries.map((country) => (
                <option key={country} value={country}>
                  {country}
                </option>
              ))}
        </select>
        {/* <CountrySelect/> */}

        <select name="scale" id="scale" onChange={handleChange} value={inputs.scale || "" } required>
          <option value="">Select scale</option>
          {scales.map((scale) => (
                <option key={scale} value={scale}>
                  1:{scale}
                </option>
              ))}
        </select>
        <textarea name="description" id="description" placeholder="Write description" value={inputs.description || ''} onChange={handleChange} required/>

          <div {...getRootProps()}>
        <input {...getInputProps()}/>
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
      {/* <Alert severity="error" onClose={handleClose}>
        <AlertTitle>Error</AlertTitle>
        {errorMessage}
      </Alert> */}

          </>
    )
  }