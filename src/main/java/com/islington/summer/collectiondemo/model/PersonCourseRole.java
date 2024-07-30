package com.islington.summer.collectiondemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PersonCourseRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Person person;
    @ManyToOne
    private Course course;
    @ManyToOne
    private Role role;
}
