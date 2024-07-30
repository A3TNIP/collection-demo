package com.islington.summer.collectiondemo.repository;

import com.islington.summer.collectiondemo.model.Person;
import com.islington.summer.collectiondemo.model.QPerson;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomPersonRepository {

    private final JPAQueryFactory queryFactory;

    public List<Person> getByLastName(String lastName) {
        QPerson person = QPerson.person;
        return queryFactory.select(person)
                .from(person)
                .where(
                        person.name.like("%" + lastName + "%")
                ).fetch();
    }
}
