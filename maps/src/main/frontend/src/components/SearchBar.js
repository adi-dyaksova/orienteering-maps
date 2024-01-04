import React, { useState } from "react";
import { FaSearch } from "react-icons/fa";
import "../styles/SearchBar.css";

const SearchBar = ({ handleSearch }) => {
  const [searchTerm, setSearchTerm] = useState({ value: "" });

  const handleChange = (event) => {
    const newValue = event.target.value;
    setSearchTerm((prevSearchTerm) => {
      return { value: newValue };
    });
    handleSearch({ value: newValue });
  };

  return (
    <>
      <div className="search-bar-wrapper">
        <div className="search-bar">
          <FaSearch className="search-icon" />
          <input
            type="text"
            placeholder="Type something"
            className="search-input"
            onChange={handleChange}
          />
        </div>
      </div>
    </>
  );
};

export default SearchBar;
