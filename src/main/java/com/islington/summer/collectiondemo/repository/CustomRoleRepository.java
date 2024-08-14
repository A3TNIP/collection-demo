package com.islington.summer.collectiondemo.repository;

import com.islington.summer.collectiondemo.model.QRole;
import com.islington.summer.collectiondemo.model.Role;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public class CustomRoleRepository extends BaseRepository<Role> {

    public CustomRoleRepository() {
        super(Role.class);
    }

    public List<Role> findAllByPage(int page, int size) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QRole role = QRole.role;
        return queryFactory.selectFrom(role)
                .limit(size)
                .offset((long) page *size)
                .fetch();
    }

    public Collection<Role> findAllRolesByNameList(List<String> nameList) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QRole role = QRole.role;

        return queryFactory.selectFrom(role)
                .where(role.name.in(nameList))
                .fetch();
    }
}
