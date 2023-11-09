package com.orienteering.maps.dao;

import com.orienteering.maps.model.Map;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public interface MapDAO extends JpaRepository<Map,Integer> {


    default int insertMap(Map map) throws IOException {
         this.save(map);
         return 1;
    }

    default public List<Map> selectAllMaps() {
    return this.findAll();
}

    default public Optional<Map> selectMapById(Integer id) {
        return this.findById(id);
    }

    default public int updateMapById(Integer id, Map newMap) {
        Optional<Map> mapToUpdate = this.findById(id);

        if (mapToUpdate.isPresent()) {
            mapToUpdate.get().setName(newMap.getName());
            mapToUpdate.get().setYear(newMap.getYear());
            mapToUpdate.get().setCountry(newMap.getCountry());
            mapToUpdate.get().setCity(newMap.getCity());
            mapToUpdate.get().setScale(newMap.getScale());
            this.save(mapToUpdate.get());
            return 1;
        }

        return 0;
    }
    default public int updateMap(Integer id, Map map) {
        Optional<Map> mapToUpdate = this.findById(id);
        if (mapToUpdate.isPresent()) {
           // map.copy(id);
            this.save(map);
            return 1;
        }
        return 0;
    }


    @Query("SELECT m FROM Map m " +
            "WHERE (:nameFilter IS NULL OR m.name = :nameFilter) " +
            "AND (:yearFilter IS NULL OR m.year = :yearFilter) " +
            "AND (:countryFilter IS NULL OR m.country = :countryFilter) " +
            "AND (:cityFilter IS NULL OR m.city = :cityFilter) " +
            "AND (:scaleFilter IS NULL OR m.scale = :scaleFilter)")
    List<Map> selectFilteredMaps( @Param("nameFilter") String nameFilter,
                                @Param("yearFilter") Integer yearFilter,
                                 @Param("countryFilter") String countryFilter,
                                 @Param("cityFilter") String cityFilter,
                                 @Param("scaleFilter") Integer scaleFilter);

    //@Transactional
    default void uploadFile(Integer mapId, MultipartFile file) throws IOException {
        Optional<Map> map=this.findById(mapId);
        if(map.isPresent()){
            if(map.get().getFolderName().equals("D:\\maps\\maps\\src\\main\\java\\com\\orienteering\\maps\\filesystem\\")){
                map.get().setFolderName(map.get().getFolderName()+map.get().getMapId()+"\\");
                this.updateMapById(mapId,map.get());
                Files.createDirectories(Paths.get(map.get().getFolderName()));
            }
            // newfileName = id+extension
            String newfileName = map.get().getMapId() + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'));
            file.transferTo(new File(map.get().getFolderName()+newfileName));
            // TODO: change folderName in db if file not uploaded and folder empty , ??@Transactional
        }
    }

    default byte[] getMapFile(Integer mapId) throws IOException {
        Optional<Map> map=this.findById(mapId);
        if(map.isPresent()){
            //get name of first file in directory
            String fileName = null;
            String pathName = map.get().getFolderName();
            try ( DirectoryStream<Path> ds = Files.newDirectoryStream(Paths.get(pathName))) {
                Iterator<Path> fileIterator = ds.iterator();
                if (fileIterator.hasNext()) {
                    fileName = fileIterator.next().getFileName().toString();
                }
                else{ //fileName=null
                        return null;
                }
            } catch (IOException ex) {
                System.err.println("Error at Files.newDirectoryStream(Paths.get(pathName))");
            }
            pathName+=fileName;
            return java.nio.file.Files.readAllBytes(new File(pathName).toPath());
        }
        return null;
    }

}
