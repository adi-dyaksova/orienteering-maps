import { useState, useEffect } from "react";
import "../styles/Filters.css";
import countries from '../countries'

export default function Filters({ handleMapList }) {
  const [inputs, setInputs] = useState({});
  const [resetFilters, setResetFilters] = useState(false);
  
  const years = Array.from(
    { length: 123 },
    (_, index) => new Date().getFullYear() - index
  );

  const allCountries=countries.map(country => country.name);
  const scales = [4000,5000,7500,10000,15000];


  useEffect(() => {
    if (resetFilters) {
      handleMapList({});
      setResetFilters(false); // Reset the flag after handling the reset
    }
  }, [resetFilters, handleMapList]);

  const handleChange = (event) => {
    const name = event.target.name;
    let value;

    if(event.target.type === 'checkbox'){
      value= event.target.checked;
    }
    else{
      value = event.target.value;
    }
    setInputs((values) => ({ ...values, [name]: value }));
   console.log(inputs);
  };
 

  function handleReset() {
    setInputs({});
     setResetFilters(true); // Set the flag to indicate reset
  }


  function handleSubmit(event) {
    event.preventDefault();
     handleMapList(inputs);
  
  }

  return (
    
      <form className="filters-form" onSubmit={handleSubmit}>

        {/* <input type="text" name="name" id="name" placeholder="Name" value={inputs.name || ''} onChange={handleChange} /> */}

        {/* <input type="number" id="year" name="year" placeholder="Year" min="1900" max="2030" step="1" value={inputs.year || ''} onChange={handleChange}></input> */}
        <select id="year" name="year" value={inputs.year || ''} onChange={handleChange}>
              <option value="">Select year</option>
              {years.map((year) => (
                <option key={year} value={year}>
                  {year}
                </option>
              ))}
            </select>
        {/* <input type="text" name="city" id="city" placeholder="City" value={inputs.city || ''} onChange={handleChange} /> */}
        <select name="country" id="country" onChange={handleChange} value={inputs.country || ""}>
          <option value="">Select country</option>
          {allCountries.map((country) => (
                <option key={country} value={country}>
                  {country}
                </option>
              ))}
        </select>
        {/* <CountrySelect/> */}
        {/* <input type="number" id="scale" name="scale" placeholder="Scale" value={inputs.scale || ''} onChange={handleChange}></input> */}
        <select name="scale" id="scale" onChange={handleChange} value={inputs.scale || ""}>
          <option value="">Select scale</option>
          {scales.map((scale) => (
                <option key={scale} value={scale}>
                  1:{scale}
                </option>
              ))}
        </select>


        <div className="filter-btns-wrapper">
          <button type="submit">Save </button>
          <button className="reset-btn" onClick={handleReset}>Reset </button>
        </div>
      </form>
    
  );
}
