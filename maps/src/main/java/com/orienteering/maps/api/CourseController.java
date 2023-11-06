package com.orienteering.maps.api;

import com.orienteering.maps.model.Course;
import com.orienteering.maps.model.CourseSearchCriteria;
import com.orienteering.maps.model.Map;
import com.orienteering.maps.model.MapSearchCriteria;
import com.orienteering.maps.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/course")
@RestController

public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService=courseService;
    }

    @PostMapping
    public void addCourse(@Valid @NonNull @RequestBody Course course){
        this.courseService.insertCourse(course);
    }

    @GetMapping
    public List<Course> getAllCourses(){
        return this.courseService.getAllCourses();
    }

    @GetMapping(path = "{id}")
    public Course getCourseById( @PathVariable("id") Integer id){
        return courseService.getCourseById(id)
                .orElse(null);
    }

    @PutMapping(path = "{id}")
    public void updateCourseById(@PathVariable("id") Integer id,@Valid @NonNull @RequestBody Course newCourse){
        courseService.updateCourseById(id,newCourse);
    }

    @PostMapping(path = "/search")
    public List<Course> getFilteredCourses( @Valid @NonNull @RequestBody CourseSearchCriteria courseSearchCriteria){ //???? @Valid @NonNull
        return this.courseService.getFilteredCourses(courseSearchCriteria);
    }

    @GetMapping(path = "/search/{id}")
    public List<Course> getAllCoursesByMapId(@PathVariable("id") Integer mapId){
        return  this.courseService.getAllCoursesByMapId(mapId);
    }
}
