package com.example.springdatajpa02.dto;

import com.example.springdatajpa02.domain.User;
import com.example.springdatajpa02.embedded.Address;
import com.example.springdatajpa02.enumType.UserGender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {
    private String loginId;
    private String password;
    private String name;
    private String address;
    private String addressDetail;
    private String gender;

    public User toEntity(){
        Address embeddedType = new Address(address, addressDetail);

        return User.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .gender(UserGender.stringToUserGender(gender))
                .address(embeddedType)
                .build();
    }

}
