package com.url.shortener.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.url.shortener.models.User;

import java.util.Collection;
import java.util.Collections;

//import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Data;
import lombok.NoArgsConstructor;
 @Data
 @NoArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID=1L;
    private Long id;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
     
    public UserDetailsImpl(Long id, String username, String email, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }


    public static UserDetailsImpl build(User user){
        GrantedAuthority authority=new SimpleGrantedAuthority(user.getRole());
        return new UserDetailsImpl(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            Collections.singletonList(authority)
        );
    }

    @Override
    public String getUsername() {
        return this.username; // or however you store the username
    }

    @Override
    public String getPassword() {
        return this.password; // or however you store the password
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities; // or however you store the authorities
    }
}