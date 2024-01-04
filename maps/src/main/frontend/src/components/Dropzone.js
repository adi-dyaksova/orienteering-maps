import React, { useCallback } from "react";
import { useDropzone } from "react-dropzone";

export default function Dropzone({ id }) {
  const onDrop = useCallback((acceptedFiles) => {
    const file = acceptedFiles[0];
    console.log(file);
    const formData = new FormData();
    formData.append("file", file);
    console.log("formadata", formData[0]);
  }, []);

  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      <p>
        {isDragActive
          ? "Drop the files here ..."
          : "Drag 'n' drop some files here, or click to select files"}
      </p>
    </div>
  );
}
