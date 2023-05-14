package com.example.springdatajpa02.domain;

import com.example.springdatajpa02.embedded.Address;
import com.example.springdatajpa02.enumType.UserGender;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    void setUp(){
        Address address = new Address("강남구", "301호");

        user = User.builder()
                .loginId("aaa")
                .password("1234")
                .name("홍길동")
                .gender(UserGender.M)
                .address(address)
                .build();
    }
    @Test
    @DisplayName("회원 정보 저장")
    void save(){
        User savedUser = userRepository.save(user);

        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    @DisplayName("회원 조회")
    void findById(){
        User savedUser = userRepository.save(user);
        User foundUser = userRepository.findById(savedUser.getId()).get();

        assertThat(savedUser.getId()).isEqualTo(foundUser.getId());
    }

    @Test
    @DisplayName("아이디 비밀번호로 회원 조회")
    void findByLoginIdAndPw(){
        User savedUser = userRepository.save(user);
        User foundUser = userRepository.findByLoginIdAndPw(user.getLoginId(), user.getPassword()).get();

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getLoginId().equals(user.getLoginId()));
    }

}