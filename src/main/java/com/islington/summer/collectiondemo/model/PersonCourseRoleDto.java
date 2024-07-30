package com.islington.summer.collectiondemo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonCourseRoleDto {
    private Long id;
    private PersonDto personDto;
    private CourseDto courseDto;
    private RoleDto roleDto;
}
