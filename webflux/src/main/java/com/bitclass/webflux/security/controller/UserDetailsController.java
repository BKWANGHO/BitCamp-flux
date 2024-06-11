package com.bitclass.webflux.security.controller;


import com.bitclass.webflux.security.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Log
@Component
@RequiredArgsConstructor
public class UserDetailsController implements AuthenticationProvider {

    private final UserDetailsServiceImpl detailsServiceImpl;

    @Value("${jwt.secret}")
    private String securityKey;

    @Value("${jwt.expiration}")
    private long validityInMs = 3600000;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
