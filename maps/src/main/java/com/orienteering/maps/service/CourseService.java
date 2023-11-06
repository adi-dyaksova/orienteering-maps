package com.orienteering.maps.service;

import com.orienteering.maps.dao.CourseDAO;
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
    private final CourseDAO courseDAO;

    @Autowired
    public CourseService(CourseDAO courseDAO ){
        this.courseDAO=courseDAO;
    }


    public int insertCourse(Course course){
       return this.courseDAO.insertCourse(course);
    }

    public List<Course> getAllCourses(){
        return this.courseDAO.selectAllCourses();
    }

    public Optional<Course> getCourseById(Integer id){
        return courseDAO.selectCourseById(id);
    }

    public int updateCourseById(Integer id, Course newCourse){
        return  courseDAO.updateCourseById(id,newCourse);
    }

    public List<Course> getFilteredCourses(CourseSearchCriteria courseSearchCriteria){
        return this.courseDAO.selectFilteredCourses(courseSearchCriteria.getCategory().orElse(null),
                courseSearchCriteria.getAgeGroup().orElse(null),
                courseSearchCriteria.getDiscipline().orElse(null),
                courseSearchCriteria.getControls().orElse(null),
                courseSearchCriteria.getDistance().orElse(null),
                courseSearchCriteria.isCompetition().orElse(null));
    }

    public List<Course> getAllCoursesByMapId(Integer mapId){
        return this.courseDAO.selectAllCoursesByMapId(mapId);
    }
}
