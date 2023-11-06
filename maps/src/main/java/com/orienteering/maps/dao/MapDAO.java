package com.orienteering.maps.dao;

import com.orienteering.maps.model.*;
import com.orienteering.maps.model.MapSearchCriteria;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MapDAO extends JpaRepository<Map,Integer> {


    default int insertMap(Map map){
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
            map.copy(id);
            this.save(map);
            return 1;
        }
        return 0;
    }


    @Query("SELECT m FROM Map m " +
            "WHERE (:yearFilter IS NULL OR m.year = :yearFilter) " +
            "AND (:countryFilter IS NULL OR m.country = :countryFilter) " +
            "AND (:cityFilter IS NULL OR m.city = :cityFilter) " +
            "AND (:scaleFilter IS NULL OR m.scale = :scaleFilter)")
    List<Map> selectFilteredMaps(@Param("yearFilter") Integer yearFilter,
                                 @Param("countryFilter") String countryFilter,
                                 @Param("cityFilter") String cityFilter,
                                 @Param("scaleFilter") Integer scaleFilter);

}
