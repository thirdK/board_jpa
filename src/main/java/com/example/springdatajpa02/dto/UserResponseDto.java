package com.example.springdatajpa02.dto;

import com.example.springdatajpa02.domain.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String address;
    private String addressDetail;
    private String gender;

    public UserResponseDto(User entity) {
        this.id = entity.getId();
        this.loginId = entity.getLoginId();
        this.password = entity.getPassword();
        this.name = entity.getName();
        this.address = entity.getAddress().getAddress();
        this.addressDetail = entity.getAddress().getAddressDetail();
        this.gender = entity.getGender().toString();
    }
}
