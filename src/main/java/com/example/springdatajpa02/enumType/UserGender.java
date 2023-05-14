package com.example.springdatajpa02.enumType;

import java.util.Arrays;
import java.util.Optional;

public enum UserGender {
    M, F, N;

    public static UserGender stringToUserGender(String userGender){
//        Arrays.stream(UserGender.values()).forEach(System.out::println);
        Optional<UserGender> result = Arrays.stream(UserGender.values()).filter(gender -> gender.toString().equals(userGender)).findAny();
        return result.orElse(UserGender.N);
    }
}
