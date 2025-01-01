package com.example.BlogHR.services.impl;


import com.example.BlogHR.AppConstants;
import com.example.BlogHR.entities.Role;
import com.example.BlogHR.entities.Users;
import com.example.BlogHR.exceptions.ApiException;
import com.example.BlogHR.exceptions.ResourceNotFoundException;
import com.example.BlogHR.payload.UserDto;
import com.example.BlogHR.repositories.RoleRepository;
import com.example.BlogHR.repositories.UserRepository;
import com.example.BlogHR.response.UserDetailResponse;
import com.example.BlogHR.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetailResponse registerUser(UserDto userDto) {
        Users user = modelMapper.map(userDto, Users.class);

//         Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

//         Set the role
        Role role = roleRepository.findById(AppConstants.ROLE_USER).get();
        user.getRoles().add(role);

        Users savedUser = userRepository.save(user);


        return modelMapper.map(savedUser, UserDetailResponse.class);
    }

//    @Override
//    public UserDetailResponse registerRegularUser(UserDto userDto) {
//        Users user = modelMapper.map(userDto, Users.class);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        Role userRole = roleRepository.findByName("ROLE_USER")
//                .orElseThrow(() -> new ApiException("ROLE_USER is not defined in the system."));
//        user.getRoles().add(userRole);
//
//        Users savedUser = userRepository.save(user);
//        return modelMapper.map(savedUser, UserDetailResponse.class);
//    }

    @Override
    public UserDetailResponse registerAdminUser(UserDto userDto) {
        if (userRepository.count() > 0) {
            throw new ApiException("Admin users can only be registered by existing admins.");
        }

        // Map UserDto to Users entity
        Users user = modelMapper.map(userDto, Users.class);

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Automatically set the ROLE_ADMIN
        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("ROLE_ADMIN");
                    role.setId(AppConstants.ROLE_ADMIN); // Ensure it matches your role constants
                    return roleRepository.save(role);
                });
        user.getRoles().add(adminRole);

        // Save user to database
        Users savedUser = userRepository.save(user);

        // Map back to UserDetailResponse
        return modelMapper.map(savedUser, UserDetailResponse.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        Users users = convertDtoToUsers(userDto);
        Users savedUser = userRepository.save(users);
        return convertUsersToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        Users users = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        users.setPassword(userDto.getPassword());
        users.setName(userDto.getName());
        users.setEmail(userDto.getEmail());
        users.setAbout(userDto.getAbout());
        Users users1 = userRepository.save(users);
        UserDto userDto1 = this.convertUsersToDto(users1);
        return userDto1;
    }

    @Override
    public UserDetailResponse getUserByUserId(Long userId) {
        Users users = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return modelMapper.map(users, UserDetailResponse.class);

    }

    @Override
    public List<UserDetailResponse> getAllUserList() {
        List<Users> users = userRepository.findAll();
        List<UserDetailResponse> userResponse = users.stream()
                .map(usr -> this.modelMapper.map(usr, UserDetailResponse.class))
                .collect(Collectors.toList());
        return userResponse;
    }

    @Override
    public void deleteUser(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepository.delete(user);
    }

    private Users convertDtoToUsers(UserDto userDto) {
        Users users = modelMapper.map(userDto, Users.class);
//        Users users = new Users();
//        users.setId(userDto.getId());
//        users.setName(userDto.getName());
//        users.setAbout(userDto.getAbout());
//        users.setEmail(userDto.getEmail());
//        users.setPassword(userDto.getPassword());
        return users;
    }

    private UserDto convertUsersToDto(Users users) {
        UserDto userDto = modelMapper.map(users, UserDto.class);
        return userDto;
    }

}
