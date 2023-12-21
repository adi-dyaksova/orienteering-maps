import React, { useState } from 'react';
import { Document, Page, pdfjs } from 'react-pdf';
import '@react-pdf-viewer/core/lib/styles/index.css';

const CroppedPdfImage = ({ filePath}) => {
  const [cropArea, setCropArea] = useState({ width: 300, height: 200 });

  // const filePath = `http://localhost:8080/api/v1/map/${mapId}/getMapFile`;

  const onPdfLoadSuccess = ({ width, height }) => {
    // Calculate the center of the first page and set the crop area accordingly
    const centerX = width / 2 - cropArea.width / 2;
    const centerY = height / 2 - cropArea.height / 2;
    setCropArea({ x: centerX, y: centerY, width: cropArea.width, height: cropArea.height });
  };

  pdfjs.GlobalWorkerOptions.workerSrc = `https://unpkg.com/pdfjs-dist@${pdfjs.version}/build/pdf.worker.min.js`;

  return (
    <div style={{ width: cropArea.width, height: cropArea.height, overflow: 'hidden' }}>
      <Document file={filePath} onLoadSuccess={onPdfLoadSuccess}>
        <Page pageNumber={1} width={cropArea.width} height={cropArea.height} />
      </Document>
    </div>
  );
};

export default CroppedPdfImage;
