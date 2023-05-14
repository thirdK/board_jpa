package com.example.springdatajpa02.controller;

import com.example.springdatajpa02.dto.UserRequestDto;
import com.example.springdatajpa02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users/v1/*")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping("/login")
    public void login(String loginId, String password, HttpServletRequest req){
        Long userId = userService.findByLoginIdAndPw(loginId, password).getId();
        req.getSession().setAttribute("userId", userId);
    }

    @PostMapping("/join")
    public void join(@RequestBody UserRequestDto userRequestDto){
        userService.save(userRequestDto.toEntity());
    }
}
