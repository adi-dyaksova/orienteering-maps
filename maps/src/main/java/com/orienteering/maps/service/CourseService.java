package com.orienteering.maps.service;

import com.orienteering.maps.dao.MapDAO;
import com.orienteering.maps.model.Course;
import com.orienteering.maps.model.CourseSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service

public class CourseService {
    private final MapDAO mapDAO;

    @Autowired
    public CourseService(@Qualifier("fakeDao") MapDAO mapDAO ){
        this.mapDAO=mapDAO;
    }

    public int insertCourse(Course course){
        return this.mapDAO.insertCourse(course);
    }

    public List<Course> getAllCourses(){
        return this.mapDAO.selectAllCourses();
    }

    public Optional<Course> getCourseById(UUID id){
        return mapDAO.selectCourseById(id);
    }

    public int updateCourseById(UUID id, Course newCourse){
        return  mapDAO.updateCourseById(id,newCourse);
    }

    public List<Course> getFilteredCourses(CourseSearchCriteria courseSearchCriteria){
        return this.mapDAO.selectFilteredCourses(courseSearchCriteria);
    }

    public List<Course> getAllCoursesByMapId(UUID mapId){
        return this.mapDAO.selectAllCoursesByMapId(mapId);
    }
}
