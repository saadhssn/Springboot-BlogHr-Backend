package com.example.BlogHR.controller;

import java.util.HashSet;
import com.example.BlogHR.AppConstants;
import com.example.BlogHR.exceptions.ApiException;
import com.example.BlogHR.payload.JwtAuthRequest;
import com.example.BlogHR.payload.JwtAuthResponse;
import com.example.BlogHR.payload.RoleDto;
import com.example.BlogHR.payload.UserDto;
import com.example.BlogHR.response.UserDetailResponse;
import com.example.BlogHR.security.JwtTokenHelper;
import com.example.BlogHR.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest jwtAuthRequest
    ) throws Exception {
        // Use email for authentication
        authenticateUser(jwtAuthRequest.getEmail(), jwtAuthRequest.getPassword());  // Authenticate with email

        // Load the user by email
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthRequest.getEmail());

        // Generate JWT token
        String token = jwtTokenHelper.generateToken(userDetails);

        // Prepare JWT response
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(token);
        jwtAuthResponse.setUser(modelMapper.map(userDetails, UserDto.class));

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<UserDetailResponse> registerUser(@Valid @RequestBody UserDto userDto) {
        UserDetailResponse response = userService.registerUser(userDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @PostMapping("/register/user")
//    public ResponseEntity<UserDetailResponse> registerUser(@Valid @RequestBody UserDto userDto) {
//        UserDetailResponse response = userService.registerRegularUser(userDto);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
//
    @PostMapping("/register/admin")
    public ResponseEntity<UserDetailResponse> registerAdmin(@Valid @RequestBody UserDto userDto) {
        UserDetailResponse response = userService.registerAdminUser(userDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private void authenticateUser(String email, String password) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)  // Use email as the username
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}