package com.islington.summer.collectiondemo.controller;

import com.islington.summer.collectiondemo.model.Course;
import com.islington.summer.collectiondemo.repository.CourseRepository;
import com.islington.summer.collectiondemo.repository.CustomCourseRepository;
import com.islington.summer.collectiondemo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseRepository courseRepository;
    private final CourseService courseService;

    @GetMapping("")
    public List<Course> getAllCourses() {
        return courseService.getAll();
    }

    @PostMapping("")
    public Course saveCourse(@RequestBody Course course) {
        return courseService.save(course);
    }

    @PutMapping("{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Course savedInstance = courseRepository.findById(id).orElseThrow();
        savedInstance.setName(course.getName());
        return courseRepository.save(savedInstance);
    }
}
