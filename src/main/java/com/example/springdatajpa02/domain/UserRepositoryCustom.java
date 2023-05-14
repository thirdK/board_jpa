package com.example.springdatajpa02.domain;

import java.util.Optional;

/*
queryDSL 연습용
 */
public interface UserRepositoryCustom {

    public Optional<User> findByLoginIdAndPw(String loginId, String password);
    public Optional<User> findByLoginId(String loginId);
}
