package com.islington.summer.collectiondemo.repository;

import com.islington.summer.collectiondemo.model.PersonCourseRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonCourseRoleRepository extends JpaRepository<PersonCourseRole, Long> {
    Optional<PersonCourseRole> findByPerson_Id(Long id);
}
