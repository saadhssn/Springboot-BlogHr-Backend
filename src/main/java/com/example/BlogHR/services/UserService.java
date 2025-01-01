package com.example.BlogHR.services;


import com.example.BlogHR.payload.UserDto;
import com.example.BlogHR.response.UserDetailResponse;

import java.util.List;

public interface UserService {
    public UserDetailResponse registerUser(UserDto user);

//    UserDetailResponse registerRegularUser(UserDto userDto);
    UserDetailResponse registerAdminUser(UserDto userDto);
    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user, Long userId);

    UserDetailResponse getUserByUserId(Long userId);

    List<UserDetailResponse> getAllUserList();

    void deleteUser(Long userId);

}
