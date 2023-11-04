package com.orienteering.maps.dao;

import com.orienteering.maps.model.Course;
import com.orienteering.maps.model.CourseSearchCriteria;
import com.orienteering.maps.model.Map;
import com.orienteering.maps.model.MapSearchCriteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository("fakeDao")
public class FakeMapDataAccessService implements MapDAO{
    private static List<Map> DB = new ArrayList<>();
    private static List<Course> DBCourses = new ArrayList<>();
    @Override
    public int insertMap(UUID id, Map map) {
        //DB.add(new Map(id,map.getYear(),map.getCountry(),map.getCity(), map.getScale(), map.isWithCourse(), map.getCategory(),map.getAgeGroup(),map.getDiscipline(), map.isCompetition()));
        DB.add(map.copy(id));
        return 1;
    }

    @Override
    public List<Map> selectAllMaps() {
        return DB;
    }

    @Override
    public Optional<Map> selectMapById(UUID id) {
        return DB.stream()
                .filter(map -> map.getId().equals(id))
                .findFirst();
    }

    @Override
    public int updateMapById(UUID id, Map map) {
        Optional<Map> mapToUpdate = selectMapById(id);
        if(mapToUpdate.isPresent()){
            //DB.set(DB.indexOf(mapToUpdate.get()), new Map(id,map.getYear(),map.getCountry(),map.getCity(), map.getScale(), map.isWithCourse(), map.getCategory(),map.getAgeGroup(),map.getDiscipline(), map.isCompetition()));
            DB.set(DB.indexOf(mapToUpdate.get()),map.copy(id));
            return 1;
        }
        return 0;
    }

    @Override
    public List<Map> selectFilteredMaps(MapSearchCriteria mapSearchCriteria) {
        //mapSearchCriteria.getCountry().equals(Optional.of(DB.get(0).getCountry()));
        //return DB;
        return DB.stream()
               // .filter(map -> mapSearchCriteria.getCountry().stream().allMatch(country -> country.equals(map.getCountry())))
                .filter(map -> mapSearchCriteria.getCountry().isPresent() ? mapSearchCriteria.getCountry().get().equals(map.getCountry()): true)
                .filter(map -> mapSearchCriteria.getCity().isPresent() ? mapSearchCriteria.getCity().get().equals(map.getCity()): true )
                .filter(map -> mapSearchCriteria.getYear().isPresent() ? mapSearchCriteria.getYear().equals(Optional.of(map.getYear())): true)
                .filter(map -> mapSearchCriteria.getScale().isPresent() ? mapSearchCriteria.getScale().equals(Optional.of(map.getScale())): true)
                .collect(Collectors.toList());
    }




    //COURSES

    @Override
    public int insertCourse(UUID id, Course course) {
        DBCourses.add(course.copy(id));
        return 1;
    }

    @Override
    public List<Course> selectAllCourses() {
        return DBCourses;
    }

    @Override
    public Optional<Course> selectCourseById(UUID id) {
        return DBCourses.stream()
                .filter(map -> map.getId().equals(id))
                .findFirst();
    }

    @Override
    public int updateCourseById(UUID id, Course course) {
        Optional<Course> courseToUpdate = selectCourseById(id);
        if(courseToUpdate.isPresent()){
            DBCourses.set(DBCourses.indexOf(courseToUpdate.get()),course.copy(id));
            return 1;
        }
        return 0;
    }

    @Override
    public List<Course> selectFilteredCourses(CourseSearchCriteria courseSearchCriteria) {

        //TODO max distance or range
        return DBCourses.stream()
                .filter(map -> courseSearchCriteria.getMapId().isPresent() ? courseSearchCriteria.getMapId().get().equals(map.getMapId()): true)
                .filter(map -> courseSearchCriteria.getCategory().isPresent() ? courseSearchCriteria.getCategory().get().equals(map.getCategory()): true )
                .filter(map -> courseSearchCriteria.getAgeGroup().isPresent() ? courseSearchCriteria.getAgeGroup().equals(Optional.of(map.getAgeGroup())): true)
                .filter(map -> courseSearchCriteria.getDiscipline().isPresent() ? courseSearchCriteria.getDiscipline().equals(Optional.of(map.getDiscipline())): true)
                .filter(map -> courseSearchCriteria.getControls().isPresent() ? courseSearchCriteria.getControls().get().equals(map.getControls()): true)
                .filter(map -> courseSearchCriteria.getDistance().isPresent() ? courseSearchCriteria.getDistance().get().equals(map.getDistance()): true)
                .filter(map -> courseSearchCriteria.isCompetition().isPresent() ? courseSearchCriteria.isCompetition().get().equals(map.isCompetition()): true)
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> selectAllCoursesByMapId(UUID mapId) {
        //защо bad request при невалидно mapId вместо празен списък
        return DBCourses.stream()
                .filter(map -> map.getMapId().equals(mapId))
                .collect(Collectors.toList());
    }

}
