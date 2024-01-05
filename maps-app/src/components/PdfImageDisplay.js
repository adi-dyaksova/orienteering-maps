import React, { useState } from "react";
import { Document, Page, pdfjs } from "react-pdf";
import "../styles/PdfImageDisplay.css";

const PdfImageDisplay = ({ pdfUrl }) => {
  const [numPages, setNumPages] = useState(null);
  const [pageNumber, setPageNumber] = useState(1);

  const onDocumentLoadSuccess = ({ numPages }) => {
    setNumPages(numPages);
  };

  pdfjs.GlobalWorkerOptions.workerSrc = `https://unpkg.com/pdfjs-dist@${pdfjs.version}/build/pdf.worker.min.js`;

  return (
    <div className="pdf-container">
      <Document file={pdfUrl} onLoadSuccess={onDocumentLoadSuccess}>
        <Page pageNumber={pageNumber} className="pdf-page" width={500} />
      </Document>
      <p className="page-info">
        Page {pageNumber} of {numPages}
      </p>
    </div>
  );
};

export default PdfImageDisplay;
