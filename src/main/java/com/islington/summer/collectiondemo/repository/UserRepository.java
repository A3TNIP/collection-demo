package com.islington.summer.collectiondemo.repository;

import com.islington.summer.collectiondemo.model.QUserEntity;
import com.islington.summer.collectiondemo.model.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends BaseRepository<UserEntity> {
    public UserRepository() {
        super(UserEntity.class);
    }

    public UserEntity findByEmail(String email) {
        JPAQueryFactory factory = new JPAQueryFactory(entityManager);
        QUserEntity qUser = QUserEntity.userEntity;
        return factory.selectFrom(qUser)
                .where(qUser.email.eq(email))
                .fetchOne();
    }
}
