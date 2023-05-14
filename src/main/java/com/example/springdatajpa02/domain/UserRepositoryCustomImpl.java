package com.example.springdatajpa02.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.example.springdatajpa02.domain.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<User> findByLoginIdAndPw(String loginId, String password) {
        User foundUser = queryFactory.selectFrom(user)
                    .where(user.loginId.eq(loginId).and(user.password.eq(password)))
                    .fetchOne();

        return Optional.of(foundUser);
    }

    @Override
    public Optional<User> findByLoginId(String loginId) {
        User foundUser = queryFactory.selectFrom(user)
                .where(user.loginId.eq(loginId))
                .fetchOne();

        return Optional.of(foundUser);
    }
}
