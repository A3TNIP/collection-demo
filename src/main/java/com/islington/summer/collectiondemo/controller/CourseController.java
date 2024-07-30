package com.islington.summer.collectiondemo.controller;

import com.islington.summer.collectiondemo.model.Course;
import com.islington.summer.collectiondemo.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseRepository courseRepository;

    @GetMapping("")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping("")
    public Course saveCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @PutMapping("{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Course savedInstance = courseRepository.findById(id).orElseThrow();
        savedInstance.setName(course.getName());
        return courseRepository.save(savedInstance);
    }
}
