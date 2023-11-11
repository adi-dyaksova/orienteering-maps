package com.orienteering.maps.dao;

import com.orienteering.maps.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseDAO extends CourseDAOInterface, JpaRepository<Course, Integer> {
    default int insertCourse(Course course) {
        this.save(course);
        return 1;
    }

    default List<Course> getAllCourses() {
        return this.findAll();
    }


    default Optional<Course> getCourseById(Integer id) {
        return this.findById(id);
    }


     default int updateCourseById(Integer id, Course newCourse) {
        Optional<Course> courseToUpdate = this.findById(id);

        if (courseToUpdate.isPresent()) {
            courseToUpdate.get().setCategory(newCourse.getCategory());
            courseToUpdate.get().setAgeGroup(newCourse.getAgeGroup());
            courseToUpdate.get().setDiscipline(newCourse.getDiscipline());
            courseToUpdate.get().setControls(newCourse.getControls());
            courseToUpdate.get().setDistance(newCourse.getDistance());
            courseToUpdate.get().setIsCompetition(newCourse.isCompetition());
            this.save(courseToUpdate.get());
            return 1;
        }

        return 0;
    }

    @Query("SELECT c FROM Course c " +
            "WHERE (:categoryFilter IS NULL OR c.category = :categoryFilter) " +
            "AND (:ageGroupFilter IS NULL OR c.ageGroup = :ageGroupFilter) " +
            "AND (:disciplineFilter IS NULL OR c.discipline = :disciplineFilter) " +
            "AND (:controlsFilter IS NULL OR c.controls = :controlsFilter)"+
            "AND (:distanceFilter IS NULL OR c.distance = :distanceFilter)"+
            "AND (:isCompetitionFilter IS NULL OR c.isCompetition = :isCompetitionFilter)")
     List<Course> getFilteredCourses(@Param("categoryFilter") Course.Category categoryFilter,
                                        @Param("ageGroupFilter") Course.AgeGroup ageGroupFilter,
                                        @Param("disciplineFilter") Course.Discipline disciplineFilter,
                                        @Param("controlsFilter") Integer controlsFilter,
                                        @Param("distanceFilter") Double distanceFilter,
                                        @Param("isCompetitionFilter") Boolean isCompetitionFilter);


    @Query("SELECT c FROM Course c " +
            "WHERE c.mapId = :map_id ")
    List<Course> getAllCoursesByMapId(@Param("map_id") Integer map_id);

}
