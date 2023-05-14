package com.example.springdatajpa02.domain;

import com.example.springdatajpa02.embedded.Address;
import com.example.springdatajpa02.enumType.UserGender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TBL_USER")
public class User {
    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;
    private String loginId;
    private String password;
    private String name;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private UserGender gender = UserGender.N;

    @Builder
    public User(String loginId, String password, String name, Address address, UserGender gender) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.gender = gender;
    }

    public Address stringToAddress(String addressStr, String addressDetailStr){
        return address = new Address(addressStr, addressDetailStr);
    }
}
