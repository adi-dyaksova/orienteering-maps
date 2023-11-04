package com.orienteering.maps.dao;

import com.orienteering.maps.model.Course;
import com.orienteering.maps.model.CourseSearchCriteria;
import com.orienteering.maps.model.Map;
import com.orienteering.maps.model.MapSearchCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("mysqlDao")
public class  MapDataAccessService implements MapDAO  {
    @Override
    public int insertMap(UUID id, Map map) {
        return 0;
    }

    @Override
    public List<Map> selectAllMaps() {
        return null;
    }

    @Override
    public Optional<Map> selectMapById(UUID id) {
        return Optional.empty();
    }

    @Override
    public int updateMapById(UUID id, Map map) {
        return 0;
    }

    @Override
    public List<Map> selectFilteredMaps(MapSearchCriteria mapSearchCriteria) {
        return null;
    }

    @Override
    public int insertCourse(UUID id, Course course) {
        return 0;
    }

    @Override
    public List<Course> selectAllCourses() {
        return null;
    }

    @Override
    public Optional<Course> selectCourseById(UUID id) {
        return Optional.empty();
    }

    @Override
    public int updateCourseById(UUID id, Course course) {
        return 0;
    }

    @Override
    public List<Course> selectFilteredCourses(CourseSearchCriteria courseSearchCriteria) {
        return null;
    }

    @Override
    public List<Course> selectAllCoursesByMapId(UUID mapId) {
        return null;
    }
}
