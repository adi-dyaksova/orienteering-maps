package com.orienteering.maps.dao;

import com.orienteering.maps.model.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.*;

public interface MapDAO extends MapDAOInterface, JpaRepository<Map,Integer> {

    default int insertMap(Map map) {
         this.save(map);
         return 1;
    }

    default List<Map> getAllMaps() {
    return this.findAll();
}

    default Optional<Map> getMapById(Integer id) {
        return this.findById(id);
    }

    default int updateMapById(Integer id, Map newMap) {
        Optional<Map> mapToUpdate = this.findById(id);

        if (mapToUpdate.isPresent()) {
            mapToUpdate.get().setName(newMap.getName());
            mapToUpdate.get().setYear(newMap.getYear());
            mapToUpdate.get().setCountry(newMap.getCountry());
            mapToUpdate.get().setCity(newMap.getCity());
            mapToUpdate.get().setScale(newMap.getScale());
            mapToUpdate.get().setDescription(newMap.getDescription());
            this.save(mapToUpdate.get());
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
    List<Map> getFilteredMaps( @Param("nameFilter") String nameFilter,
                                @Param("yearFilter") Integer yearFilter,
                                 @Param("countryFilter") String countryFilter,
                                 @Param("cityFilter") String cityFilter,
                                 @Param("scaleFilter") Integer scaleFilter);
}
