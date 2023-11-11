package com.orienteering.maps.api;

import com.orienteering.maps.model.Course;
import com.orienteering.maps.model.CourseSearchCriteria;
import com.orienteering.maps.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("api/v1/course")
@RestController

public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService=courseService;
    }

    @PostMapping
    public void insertCourse(@Valid @NonNull @RequestBody Course course){
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

    @PostMapping(path = "/{id}/uploadCourseFile")
    public void uploadCourseFile(@PathVariable("id") Integer courseId, @RequestParam("file") MultipartFile file) throws IOException {
        courseService.uploadCourseFile(courseId,file);
    }

    @GetMapping(path = "/{id}/getCourseFile")
    public ResponseEntity<byte[]> getCourseFile(@PathVariable("id") Integer courseId) throws IOException {
        byte[] data= courseService.getCourseFile(courseId);
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }


}
