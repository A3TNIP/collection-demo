package com.islington.summer.collectiondemo.repository;

import com.islington.summer.collectiondemo.model.Person;
import com.islington.summer.collectiondemo.model.QPerson;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomPersonRepository {

    private final JPAQueryFactory queryFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> getByLastName(String lastName) {

        JPAQueryFactory factory = new JPAQueryFactory(entityManager);

        QPerson person = QPerson.person;
        return factory.select(person)
                .from(person)
                .where(
                        person.name.like("%" + lastName + "%")
                ).fetch();
    }
}
