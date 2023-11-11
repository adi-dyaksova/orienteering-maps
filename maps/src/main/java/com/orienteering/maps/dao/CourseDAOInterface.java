package com.orienteering.maps.dao;

import com.orienteering.maps.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseDAOInterface {
   int insertCourse(Course course);
    List<Course> getAllCourses();

    Optional<Course> getCourseById(Integer id);

    int updateCourseById(Integer id, Course newCourse);

    List<Course> getFilteredCourses(Course.Category categoryFilter,
                                    Course.AgeGroup ageGroupFilter,
                                    Course.Discipline disciplineFilter,
                                    Integer controlsFilter,
                                    Double distanceFilter,
                                    Boolean isCompetitionFilter);

    List<Course> getAllCoursesByMapId(Integer map_id);
}
