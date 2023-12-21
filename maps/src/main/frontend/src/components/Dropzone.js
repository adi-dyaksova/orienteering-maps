import React, {useCallback} from "react";

import axios from "axios"; 
import {useDropzone} from 'react-dropzone'


export default function Dropzone({id}) {
    const onDrop = useCallback(acceptedFiles => {
     const file = acceptedFiles[0];
     console.log(file);
     const formData = new FormData();
     formData.append("file",file); //"file" is from @RequestParam in backend - uploadMapFile
  console.log("formadata", formData[0]);
  
    // axios.post(`http://localhost:8080/api/v1/map/${id}/uploadMapFile`,
    //             formData,
    //             {
    //               headers: {
    //                 "Content-Type": "multipart/form-data"
    //               }
    //             }
    //             ).then (() => {
    //               console.log("file uploaded successfully")
    //             }).catch( err => {
    //               console.log(err);
    //             });
    }, []);
  
    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})
  
    return (
      <div {...getRootProps()}>
        <input {...getInputProps()} />
        <p>
        {isDragActive
          ? "Drop the files here ..."
          : "Drag 'n' drop some files here, or click to select files"}
      </p>
      </div>
    )
  }