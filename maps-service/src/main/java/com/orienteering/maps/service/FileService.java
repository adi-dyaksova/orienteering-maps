package com.orienteering.maps.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

@Service
public class FileService {

    private static final String PATH= "C:\\Users\\PC\\Desktop\\projects\\orienteering-maps-spring-boot\\maps-service\\src\\main\\java\\com\\orienteering\\maps\\filesystem\\";

    private String getFullDirectoryPath(String folderName){
        return PATH+folderName+"\\";
    }
    public void uploadFile(String folderName, MultipartFile file) throws IOException {
       String fullDirectoryPath = getFullDirectoryPath(folderName);
        Files.createDirectories(Paths.get(fullDirectoryPath));
        // newFileName = id+extension
        //String newFileName = id + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'));
        String newFileName = file.getOriginalFilename();
        file.transferTo(new File(fullDirectoryPath+newFileName));
    }

    public byte[] getFile(String folderName) throws IOException {

            //get name of first file in directory
            String fileName = null;
            String fullDirectoryPath = getFullDirectoryPath(folderName);
            try ( DirectoryStream<Path> ds = Files.newDirectoryStream(Paths.get(fullDirectoryPath))) {
                Iterator<Path> fileIterator = ds.iterator();
                if (fileIterator.hasNext()) {
                    fileName = fileIterator.next().getFileName().toString();
                }
                else{ //fileName=null
                    return null;
                }
            } catch (IOException ex) {
                System.err.println("Error at Files.newDirectoryStream(Paths.get(fullDirectoryPath))");
            }
            return java.nio.file.Files.readAllBytes(new File(fullDirectoryPath+fileName).toPath());
    }
}
