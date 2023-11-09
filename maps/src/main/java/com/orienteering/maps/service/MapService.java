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
import java.util.UUID;

@Service
public class MapService {
    private final MapDAO mapDAO;

    @Autowired
    public MapService( MapDAO mapDAO ){
        this.mapDAO=mapDAO;
    }

    public int insertMap(Map map) throws IOException {
        return this.mapDAO.insertMap(map);
    }

    public List<Map> getAllMaps(){
        return this.mapDAO.selectAllMaps();
    }

    public Optional<Map> getMapById(Integer id){
        return mapDAO.selectMapById(id);
    }

    public int updateMapById(Integer id, Map newMap){
        return  mapDAO.updateMapById(id,newMap);
    }

    public List<Map> getFilteredMaps(MapSearchCriteria mapSearchCriteria) {
        return mapDAO.selectFilteredMaps(
                mapSearchCriteria.getName().orElse(null),
                mapSearchCriteria.getYear().orElse(null),
                mapSearchCriteria.getCountry().orElse(null),
                mapSearchCriteria.getCity().orElse(null),
                mapSearchCriteria.getScale().orElse(null)
        );
    }

    public void uploadFile(Integer mapId, MultipartFile file) throws IOException {
        this.mapDAO.uploadFile(mapId,file);
    }

    public byte[] getMapFile(Integer mapId) throws IOException {
        return  this.mapDAO.getMapFile(mapId);
    }
}
