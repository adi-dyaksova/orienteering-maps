import React, {useState} from "react";
import axios from "axios"; 
export default function Form (){
    const [inputs, setInputs] = useState({});
  
    const handleChange = (event) => {
      const name = event.target.name;
      const value = event.target.value;
      setInputs(values => ({...values, [name]: value}))
    }
  
    const handleSubmit = (event) => {
      event.preventDefault();
      console.log(inputs);
  
  
      axios.post(`http://localhost:8080/api/v1/map`,
                  inputs
                ).then (() => {
                    console.log("map added successfully")
                }).catch( err => {
                    console.log(err);
                });
    }
  
    return (
      <form className="addMapForm" onSubmit={handleSubmit}>
          {/* <label for="name">Name: </label> */}
          <input type="text" name="name" id="name" placeholder="Name" onChange={handleChange} />
          {/* <label for="year">Year: </label> */}
          <input type="number" id="year" name="year" placeholder="Year" min="1900" max="2030" step="1" onChange={handleChange}></input>
          {/* <label for="country">Country: </label> */}
          {/* <input type="text" name="country" id="country" placeholder="Country" onChange={handleChange} /> */}
          {/* <label for="city">City: </label> */}
          <input type="text" name="city" id="city" placeholder="City" onChange={handleChange} />
          {/* <label for="countryDropdown">Country:</label> */}
          <select name="country" id="country" onChange={handleChange}> 
              <option value="Select country">Select country</option>
              <option value="Bulgaria">Bulgaria</option> 
              <option value="Sweden">Sweden</option> 
          </select>
          {/* <label for="scale">Scale: </label> */}
          <input type="number" id="scale" name="scale" placeholder="Scale" onChange={handleChange}></input>
          {/* <label for="description">Description:</label> */}
          <textarea name="description" id="description" placeholder="Description" onChange={handleChange}></textarea>
          <button type="submit" >Submit</button>
          </form>
    )
  }