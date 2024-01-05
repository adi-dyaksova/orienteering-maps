package com.orienteering.maps.dao;

import com.orienteering.maps.model.Map;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MapDAOInterface {
    int insertMap(Map map);

    List<Map> getAllMaps();

    Optional<Map> getMapById(Integer id);

     int updateMapById(Integer id, Map newMap);

    List<Map> getFilteredMaps( String nameFilter,
                              Integer yearFilter,
                               String countryFilter,
                               String cityFilter,
                               Integer scaleFilter);
    List<Map> getSearchedMaps(String searchTermValue);
}
