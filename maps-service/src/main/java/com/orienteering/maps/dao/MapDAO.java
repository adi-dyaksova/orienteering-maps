package com.orienteering.maps.dao;

import com.orienteering.maps.model.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.*;

public interface MapDAO extends MapDAOInterface, JpaRepository<Map,Integer> {

    default int insertMap(Map map) {
        Map result = this.save(map);
         return result.getMapId();
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
            "WHERE (:nameFilter IS NULL OR :nameFilter = '' OR m.name = :nameFilter) " +
            "AND (:yearFilter IS NULL OR :yearFilter = '' OR m.year = :yearFilter) " +
            "AND (:countryFilter IS NULL OR :countryFilter = '' OR m.country = :countryFilter) " +
            "AND (:cityFilter IS NULL OR :cityFilter = '' OR m.city = :cityFilter) " +
            "AND (:scaleFilter IS NULL OR :scaleFilter = '' OR m.scale = :scaleFilter)")
    List<Map> getFilteredMaps( @Param("nameFilter") String nameFilter,
                                @Param("yearFilter") Integer yearFilter,
                                 @Param("countryFilter") String countryFilter,
                                 @Param("cityFilter") String cityFilter,
                                 @Param("scaleFilter") Integer scaleFilter);
    @Query("SELECT m FROM Map m " +
            "WHERE"+
            "(:searchTermValue IS NULL OR :searchTermValue = '') OR " +
            "LOWER(m.name) LIKE CONCAT('%', LOWER(:searchTermValue), '%') " +
            "OR LOWER(m.country) LIKE CONCAT('%', LOWER(:searchTermValue), '%') " +
            "OR LOWER(m.city) LIKE CONCAT('%', LOWER(:searchTermValue), '%')" +
            "OR LOWER(m.description) LIKE CONCAT('%', LOWER(:searchTermValue), '%') " )
    List<Map> getSearchedMaps(@Param("searchTermValue") String searchTermValue);
}
