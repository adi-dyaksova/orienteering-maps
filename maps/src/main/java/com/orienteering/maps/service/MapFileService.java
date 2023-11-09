/*package com.orienteering.maps.service;

import com.orienteering.maps.dao.MapFileDAO;
import com.orienteering.maps.model.MapFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.io.File;

@Service
public class MapFileService {
    @Autowired
    private MapFileDAO mapFileDAO;
    private final String FILE_PATH = "D:\\maps\\maps\\src\\main\\java\\com\\orienteering\\maps\\filesystem\\";

    public MapFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                //throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                System.out.println("Sorry! Filename contains invalid path sequence " + fileName);
            }

            MapFile dbFile = new MapFile(fileName, file.getContentType(), file.getBytes());
            return mapFileDAO.save(dbFile);
        } catch (IOException ex) {
            //throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
            System.out.println("Could not store file " + fileName + ". Please try again!");
        }
        //? needed because of exceptions??
        return null;
    }

    public MapFile getFile(Integer fileId) {
        return mapFileDAO.findById(fileId).orElse(null);
              // .orElseThrow(() - > new FileNotFoundException("File not found with id " + fileId));
    }

    public byte[] getFileBytesById(Integer id){
        Optional<MapFile> mapFile = mapFileDAO.findById(id);
        if( mapFile.isPresent()){
            return mapFile.get().getData();
        }
        return null;
    }
    public MapFile storeDataIntoFileSystem(MultipartFile file) throws IOException {
        String filePath = FILE_PATH + file.getOriginalFilename();

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                //throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                System.out.println("Sorry! Filename contains invalid path sequence " + fileName);
            }

            MapFile dbFile = new MapFile(fileName, file.getContentType(), file.getBytes());

            file.transferTo(new File(filePath));

            return mapFileDAO.save(dbFile);
        } catch (IOException ex) {
            //throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
            System.out.println("Could not store file " + fileName + ". Please try again!");
        }
        //? needed because of exceptions??
        return null;

    }
}

 */


