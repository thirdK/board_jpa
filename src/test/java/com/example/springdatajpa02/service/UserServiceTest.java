package com.example.springdatajpa02.service;

import com.example.springdatajpa02.domain.User;
import com.example.springdatajpa02.domain.UserRepository;
import com.example.springdatajpa02.embedded.Address;
import com.example.springdatajpa02.enumType.UserGender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    private User user;

    @BeforeEach
    void setUp(){
        Address address = new Address("강남구", "301호");

        user = User.builder().loginId("aaa")
                .password("1234")
                .name("홍길동")
                .gender(UserGender.M)
                .address(address)
                .build();
    }

    @Test
    @DisplayName("회원 저장")
    void save() {
        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        //id 설정이 불가능해서 npe발생하는데 해결할 방법이 없음
        //User엔티티 builder로 id를 설정하게 만드는것 말고 없는듯?
        //npe검사
        assertThatNullPointerException().isThrownBy(() -> userService.save(user));

        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("회원 저장 아이디 중복 예외처리 확인")
    void saveDuplicateUser() {
        when(userRepository.findByLoginId(any(String.class)))
                .thenReturn(Optional.of(user));

        assertThatIllegalArgumentException().isThrownBy(()->userService.save(user))
                .withMessage("중복된 회원 아이디");
    }

    @Test
    @DisplayName("회원 번호로 조회")
    void findById() {
        when(userRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(user));
        User foundUser = userRepository.findById(1L).get();

        assertThat(foundUser.getName()).isEqualTo(user.getName());
    }

    @Test
    @DisplayName("로그인")
    void findByLoginIdAndPw() {
        when(userRepository.findByLoginIdAndPw(any(String.class), any(String.class)))
                .thenReturn(Optional.of(user));

        User foundUser = userRepository.findByLoginIdAndPw("aaa", "1234").get();

        assertThat(foundUser.getLoginId()).isEqualTo(user.getLoginId());
    }

    @Test
    @DisplayName("회원 성별 문자열 -> enumType 변환 테스트")
    void test(){
        System.out.println("======================================");
        System.out.println(UserGender.stringToUserGender("a"));
        System.out.println("======================================");
    }
}