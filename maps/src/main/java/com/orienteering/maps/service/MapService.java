package com.orienteering.maps.service;

import com.orienteering.maps.dao.MapDAO;
import com.orienteering.maps.model.Map;
import com.orienteering.maps.model.MapSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MapService {
    private final MapDAO mapDAO;

    @Autowired
    public MapService(@Qualifier("mysqlDao") MapDAO mapDAO ){
        this.mapDAO=mapDAO;
    }

    public int insertMap(Map map){
        return this.mapDAO.insertMap(map);
    }

    public List<Map> getAllMaps(){
        return this.mapDAO.selectAllMaps();
    }

    public Optional<Map> getMapById(UUID id){
        return mapDAO.selectMapById(id);
    }

    public int updateMapById(UUID id, Map newMap){
        return  mapDAO.updateMapById(id,newMap);
    }

    public List<Map> getFilteredMaps(MapSearchCriteria mapSearchCriteria){
        return this.mapDAO.selectFilteredMaps(mapSearchCriteria);
    }
}
