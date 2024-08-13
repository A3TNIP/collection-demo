package com.islington.summer.collectiondemo.controller;

import com.islington.summer.collectiondemo.model.Course;
import com.islington.summer.collectiondemo.repository.CourseRepository;
import com.islington.summer.collectiondemo.repository.CustomCourseRepository;
import com.islington.summer.collectiondemo.service.BaseService;
import com.islington.summer.collectiondemo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController extends BaseController<Course> {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        super(courseService);
        this.courseService = courseService;
    }
}
