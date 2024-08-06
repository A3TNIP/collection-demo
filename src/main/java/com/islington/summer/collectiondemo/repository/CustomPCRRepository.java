package com.islington.summer.collectiondemo.repository;

import com.islington.summer.collectiondemo.model.PersonCourseRole;
import com.islington.summer.collectiondemo.model.QPersonCourseRole;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomPCRRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public PersonCourseRole findInstance(Long personId, Long courseId, Long roleId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QPersonCourseRole personCourseRole = QPersonCourseRole.personCourseRole;

        BooleanBuilder expression = new BooleanBuilder();

        if (personId != null) {
            expression.and(personCourseRole.person.id.eq(personId));
        }
        if (courseId != null) {
            expression.and(personCourseRole.course.id.eq(courseId));
        }
        if (roleId != null) {
            expression.and(personCourseRole.role.id.eq(roleId));
        }

//        // Update
//        queryFactory.update(personCourseRole)
//                .set(personCourseRole.course.name, "value")
//                .where(personCourseRole.course.name.eq("some value"))
//                .execute();
//
//        // Delete
//        queryFactory.delete(personCourseRole)
//                .where(new BooleanBuilder())
//                .execute();

        return queryFactory.select(personCourseRole)
                .from(personCourseRole)
                .where(expression)
                .fetchOne();


    }
}
