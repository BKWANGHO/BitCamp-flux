package com.bitclass.webflux.security.domain;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginDto {
    private String username; // email로 대체 가능
    private String password;
}
