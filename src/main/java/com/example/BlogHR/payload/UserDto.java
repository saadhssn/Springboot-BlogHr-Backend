package com.example.BlogHR.payload;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100, message = "User name must be minimum 4 characters and max 100 characters")
    private String name;

    @NotBlank
    @Size(min = 4, max = 500, message = "About must be minimum 4 characters and max 500 characters")
    private String about;

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

    @NotNull
    @Email(message = "Valid email required!")
    private String email;


    @NotBlank
    //@Size(min = 4, max = 10, message = "Password must be minimum 4 characters and max 10 characters")
    private String password;

    private String role;

    private Set<RoleDto> roles = new HashSet<>();

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    //    @JsonIgnore
//    public String getPassword() {
//        return password;
//    }
}

