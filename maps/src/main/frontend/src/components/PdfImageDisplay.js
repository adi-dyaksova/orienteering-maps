import React, { useState } from 'react';
import { Document, Page, pdfjs } from 'react-pdf';

const PdfImageDisplay = ({ pdfUrl }) => {
  const [numPages, setNumPages] = useState(null);
  const [pageNumber, setPageNumber] = useState(1);

  const onDocumentLoadSuccess = ({ numPages }) => {
    setNumPages(numPages);
  };

  pdfjs.GlobalWorkerOptions.workerSrc = `https://unpkg.com/pdfjs-dist@${pdfjs.version}/build/pdf.worker.min.js`;

  return (
    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', marginTop:'2em', border:'1px solid #ccc'}}>
      <Document file={pdfUrl} onLoadSuccess={onDocumentLoadSuccess}>
        <Page pageNumber={pageNumber} />
      </Document>
      <p style={{ margin: 0 }}>
        Page {pageNumber} of {numPages}
      </p>
    </div>
  );
};

export default PdfImageDisplay;
