package com.islington.summer.collectiondemo.repository;

import com.islington.summer.collectiondemo.model.Course;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class CustomCourseRepository extends BaseRepository<Course> {
    public CustomCourseRepository() {
        super(Course.class);
    }
}
