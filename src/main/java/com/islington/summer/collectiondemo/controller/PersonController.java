package com.islington.summer.collectiondemo.controller;

import com.islington.summer.collectiondemo.model.Course;
import com.islington.summer.collectiondemo.model.Person;
import com.islington.summer.collectiondemo.model.PersonCourseRole;
import com.islington.summer.collectiondemo.model.Role;
import com.islington.summer.collectiondemo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final CourseRepository courseRepository;
    private final PersonCourseRoleRepository personCourseRoleRepository;
    private final CustomPCRRepository customPCRRepository;

    @GetMapping("")
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @PostMapping("")
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @PutMapping("/{personId}/assign/{assignType}/{referencingId}")
    public PersonCourseRole assignPerson(@PathVariable Long personId,
                                         @PathVariable String assignType,
                                         @PathVariable Long referencingId,
                                         @RequestParam(required = false, name = "courseId") Long courseId,
                                         @RequestParam(required = false, name = "roleId") Long roleId
    ) {
        Person person = personRepository.findById(personId).orElseThrow(() -> new RuntimeException("Person not found"));

        PersonCourseRole personCourseRole = this.customPCRRepository.findInstance(personId, courseId, roleId);

        if (personCourseRole == null) {
            personCourseRole = new PersonCourseRole();
            personCourseRole.setPerson(person);
        }

        switch (assignType) {
            case "ROLE":
                Role role = roleRepository.findById(referencingId).orElseThrow(() -> new RuntimeException("Role not found"));
                personCourseRole.setRole(role);
                break;
            case "COURSE":
                Course course = courseRepository.findById(referencingId).orElseThrow(() -> new RuntimeException("Course not found"));
                personCourseRole.setCourse(course);
                break;
            default:
                throw new RuntimeException("Invalid assignment type");
        }

        return personCourseRoleRepository.save(personCourseRole);
    }
}
