package com.orienteering.maps.service;

//import com.orienteering.maps.dao.CourseDAOImplementation;
import com.orienteering.maps.dao.CourseDAOInterface;
import com.orienteering.maps.model.Course;
import com.orienteering.maps.model.CourseSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseDAOInterface courseDAO;
    private final FileService fileService;

    @Autowired
    public CourseService(CourseDAOInterface courseDAO, FileService fileService){
        this.courseDAO=courseDAO;
        this.fileService = fileService;
    }


    public int insertCourse(Course course){
       return this.courseDAO.insertCourse(course);
    }

    public List<Course> getAllCourses(){
        return this.courseDAO.getAllCourses();
    }

    public Optional<Course> getCourseById(Integer id){
        return courseDAO.getCourseById(id);
    }

    public int updateCourseById(Integer id, Course newCourse){
        return  courseDAO.updateCourseById(id,newCourse);
    }

    public List<Course> getFilteredCourses(CourseSearchCriteria courseSearchCriteria){
        return this.courseDAO.getFilteredCourses(courseSearchCriteria.getCategory().orElse(null),
                courseSearchCriteria.getAgeGroup().orElse(null),
                courseSearchCriteria.getDiscipline().orElse(null),
                courseSearchCriteria.getControls().orElse(null),
                courseSearchCriteria.getDistance().orElse(null),
                courseSearchCriteria.isCompetition().orElse(null));
    }

    public List<Course> getAllCoursesByMapId(Integer mapId){
        return this.courseDAO.getAllCoursesByMapId(mapId);
    }
    public void uploadCourseFile(Integer courseId, MultipartFile file) throws IOException {
        Optional<Course> course = this.getCourseById(courseId);
        if(course.isPresent()){
            this.fileService.uploadFile("courses\\"+ courseId,file);
        }
    }

    public byte[] getCourseFile(Integer courseId) throws IOException {
        Optional<Course> course = this.getCourseById(courseId);
        if(course.isPresent()){
            return this.fileService.getFile("courses\\"+courseId);
        }
        return null;
    }
}
