package com.bitclass.webflux.user.domain;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@NoArgsConstructor
public class UserModel implements UserDetails {

    @Id
    private long userId;
    private String username;
    @Size(min = 8, message = "8자리 이상 입력하시오")
    private String password;
    private String name;
    private String email;
    private String regDate;
    List <RoleModel> roles ;
    public UserModel (String email , String password , List<RoleModel> roles) {
        this.email= email ;
        this.password=password ;
        this.roles=roles ;}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        this.roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return authorities;
    }


    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }
}