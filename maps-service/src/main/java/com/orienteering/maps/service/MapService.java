package com.orienteering.maps.service;

import com.orienteering.maps.dao.MapDAO;
import com.orienteering.maps.model.Map;
import com.orienteering.maps.model.MapSearchCriteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MapService {
    private final MapDAO mapDAO;
    private final FileService fileService;

    @Autowired
    public MapService(MapDAO mapDAO, FileService fileService){
        this.mapDAO=mapDAO;
        this.fileService = fileService;
    }

    public int insertMap(Map map) {
        return this.mapDAO.insertMap(map);
    }

    public List<Map> getAllMaps(){
        return this.mapDAO.getAllMaps();
    }


    public Optional<Map> getMapById(Integer id){
        return mapDAO.getMapById(id);
    }

    public int updateMapById(Integer id, Map newMap){
        return  mapDAO.updateMapById(id,newMap);
    }

    public List<Map> getFilteredMaps(MapSearchCriteria mapSearchCriteria) {
        return mapDAO.getFilteredMaps(
                mapSearchCriteria.getName().orElse(null),
                mapSearchCriteria.getYear().orElse(null),
                mapSearchCriteria.getCountry().orElse(null),
                mapSearchCriteria.getCity().orElse(null),
                mapSearchCriteria.getScale().orElse(null)
        );
    }

    public void uploadMapFile(Integer mapId, MultipartFile file) throws IOException {
        Optional<Map> map = this.getMapById(mapId);
        if(map.isPresent()){
            this.fileService.uploadFile("maps\\"+ mapId,file);
        }
    }

    public byte[] getMapFile(Integer mapId) throws IOException {
        Optional<Map> map = this.getMapById(mapId);
        if(map.isPresent()){
            return this.fileService.getFile("maps\\"+mapId);
        }
       return null;
    }

    public List<Map> getSearchedMaps(String searchTermValue){
        return this.mapDAO.getSearchedMaps(searchTermValue);
    }
}
