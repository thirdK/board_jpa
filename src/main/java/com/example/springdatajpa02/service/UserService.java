package com.example.springdatajpa02.service;

import com.example.springdatajpa02.domain.User;
import com.example.springdatajpa02.domain.UserRepository;
import com.example.springdatajpa02.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.OptionalLong;

@Service
@RequiredArgsConstructor
public class UserService {
    private UserRepository userRepository;

    /**
     * 회원가입
     * @param user 회원 정보
     * @return 저장된 회원 번호
     * @throws IllegalArgumentException 아이디 중복 시 발생
     */
    @Transactional
    public Optional<Long> save(User user) throws IllegalArgumentException{
        Optional<User> foundUser = userRepository.findByLoginId(user.getLoginId());
        if(foundUser.isPresent()){
            throw new IllegalArgumentException("중복된 회원 아이디");
        }

        return Optional.of(userRepository.save(user).getId());
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(Long userId){
        return userRepository.findById(userId);
    }

    /**
     * 로그인
     * @param loginId
     * @param password
     * @return
     */
    @Transactional(readOnly = true)
    public UserResponseDto findByLoginIdAndPw(String loginId, String password){
        Optional<User> foundUser = userRepository.findByLoginIdAndPw(loginId, password);
        UserResponseDto userResponseDto = null;

        if(!foundUser.isPresent()){
            throw new IllegalArgumentException("존재하지 않는 회원");
        }

        userResponseDto = new UserResponseDto(foundUser.get());
        return userResponseDto;
    }
}
