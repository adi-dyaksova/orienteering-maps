package com.orienteering.maps.dao;

import com.orienteering.maps.model.*;
import com.orienteering.maps.model.MapSearchCriteria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MapDAO  { //extends JpaRepository<Map,Integer>
    int insertMap(UUID id, Map map);

    default int insertMap(Map map){
        UUID id = UUID.randomUUID();
        return insertMap(id,map);
    }

    List<Map> selectAllMaps();

    Optional<Map> selectMapById(UUID id);
    //int deleteMapById(UUID id);
    int updateMapById(UUID id, Map map);

    List<Map> selectFilteredMaps(MapSearchCriteria mapSearchCriteria);

    //COURSEs

    int insertCourse(UUID id, Course course);

    default int insertCourse(Course course){
        UUID id = UUID.randomUUID();
        return insertCourse(id,course);
    }

    List<Course> selectAllCourses();

    Optional<Course> selectCourseById(UUID id);

    int updateCourseById(UUID id, Course course);

    List<Course> selectFilteredCourses(CourseSearchCriteria courseSearchCriteria);

    List<Course> selectAllCoursesByMapId(UUID mapId);
}
