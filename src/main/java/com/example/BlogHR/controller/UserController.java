package com.example.BlogHR.controller;


import com.example.BlogHR.AppConstants;
import com.example.BlogHR.payload.UserDto;
import com.example.BlogHR.response.ApiResponse;
import com.example.BlogHR.response.UserDetailResponse;
import com.example.BlogHR.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = AppConstants.USER_PATH)
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;


    //    GET - get all users
    @GetMapping(AppConstants.USER_BASE)
    public ResponseEntity<List<UserDetailResponse>> getListOfUsers() {
        return ResponseEntity.ok(userService.getAllUserList()); // another way to send response with body and HttpStatus
    }


    //    GET - get single user by userId
    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailResponse> getUserByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserByUserId(userId));
    }


    //    PUT - update user by userId
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUserByUserId(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Long uId) { // name path variable anything now
        UserDto updateUser = userService.updateUser(userDto, uId);
        return ResponseEntity.ok(updateUser);
    }

    //    DELETE - Delete single user by userId
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUserByUserId(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(new ApiResponse("User with User id " + userId + " has been deleted", true));
    }

}
