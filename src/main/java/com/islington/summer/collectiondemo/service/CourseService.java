package com.islington.summer.collectiondemo.service;

import com.islington.summer.collectiondemo.model.Course;
import com.islington.summer.collectiondemo.repository.CustomCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends BaseService<Course>{
    private final CustomCourseRepository courseRepository;
    @Autowired
    public CourseService(CustomCourseRepository courseRepository) {
        super(courseRepository);
        this.courseRepository = courseRepository;
    }
}
