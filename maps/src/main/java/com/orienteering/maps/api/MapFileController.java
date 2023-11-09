/*package com.orienteering.maps.api;

import com.orienteering.maps.model.MapFile;
//import com.orienteering.maps.model.Response;
import com.orienteering.maps.service.MapFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;


@RestController
public class MapFileController {
    @Autowired
    private MapFileService mapFileService;

    @PostMapping("/uploadFileIntoDB")
    public ResponseEntity<MapFile> storeFilesIntoDB(@RequestParam("file") MultipartFile file) throws IOException {
        MapFile response = mapFileService.storeFile(file);
        return ResponseEntity.status(HttpStatus.OK).body(response); //TODO:
    }

    @GetMapping(path = "/getFileFromDB/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable("id") Integer id){
        byte[] data = mapFileService.getFileBytesById(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_PDF).body(data);
        //MediaType.APPLICATION_PDF
    }

    @PostMapping("/uploadFilesIntoFileSystem")
    public ResponseEntity<MapFile> uploadFileIntoFileSystem(@RequestParam("file") MultipartFile file) throws IOException {
        MapFile response = mapFileService.storeDataIntoFileSystem(file);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

 */
