/*package com.orienteering.maps.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "files")
public class MapFile {


        @Id
        @GeneratedValue()
       // @GeneratedValue(generator = "uuid")
       // @GenericGenerator(name = "uuid", strategy = "uuid2")
        private Integer id;

        private String fileName;

        private String fileType;

        @Lob
        private byte[] data;

        public MapFile() {

        }

        public MapFile(String fileName, String fileType, byte[] data) {
            this.fileName = fileName;
            this.fileType = fileType;
            this.data = data;
        }

        public Integer getId() {
            return id;
        }

        public String getFileName() {
            return fileName;
        }

        public String getFileType() {
            return fileType;
        }

        public byte[] getData() {
            return data;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }

        public void setData(byte[] data) {
            this.data = data;
        }

}

 */
