package com.example.BlogHR.response;


import com.example.BlogHR.payload.RoleDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = { "password" })
public class UserDetailResponse {

    private Long id;

    private String name;

    private String about;

    private String email;
    private String password;

    private Set<RoleDto> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    public UserDetailResponse(Long id, String name, String about, String email, String password, Set<RoleDto> roles) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}

