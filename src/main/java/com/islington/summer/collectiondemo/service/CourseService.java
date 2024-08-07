package com.islington.summer.collectiondemo.service;

import com.islington.summer.collectiondemo.model.Course;
import com.islington.summer.collectiondemo.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends BaseService<Course>{
    @Autowired
    public CourseService(BaseRepository<Course> repository) {
        super(repository);
    }
}
