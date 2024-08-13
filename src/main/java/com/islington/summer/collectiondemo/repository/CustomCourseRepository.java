package com.islington.summer.collectiondemo.repository;

import com.islington.summer.collectiondemo.model.Course;
import org.springframework.stereotype.Repository;

@Repository
public class CustomCourseRepository extends BaseRepository<Course> {

    public CustomCourseRepository() {
        super(Course.class);
    }

}
