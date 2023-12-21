
import React, { useState } from 'react';
import { FaSearch } from 'react-icons/fa';
import '../styles/SearchBar.css'; 
import MapListItem from './MapListItem';
import axios from 'axios';


const SearchBar = ({handleSearch}) => {
  const [searchTerm,setSearchTerm] = useState({value:""});
// const [maps,setMaps]=useState([]);

const handleChange = (event) => {
  setSearchTerm({value:event.target.value});
};


// function handleSearch(){ //TODO: reset button as well
//   console.log("handle search");
//   console.log(input);
  
//       axios
//         .post(`http://localhost:8080/api/v1/map/search`, input) // TODO endpoint
//         .then((res) => {
//           console.log("map searched successfully", res.data);
//           setMaps(res.data);
//         })
//         .catch((err) => {
//           console.log(err);
//         });
// }
  return (
    <>
    <div className="search-bar-wrapper">
    <div className="search-bar">
      <FaSearch className="search-icon" />
      <input type="text" placeholder="Type something" className="search-input" onChange={handleChange}/>      
    </div>
    <button className='search-btn' onClick={()=>handleSearch(searchTerm)}> Search </button>
    </div>
        {/* <div className='result-maps-wrapper'>
        {maps.length > 0 ? (
              maps.map((map) => <MapListItem key={map.id} map={map}  />)
            ) : (
              <p className="not-found-msg">No maps match the current filters.</p>
            )}
        </div> */}
        </>
  );
};

export default SearchBar;
