package com.coranavirus.coronavirus.service.impl;

import com.coranavirus.coronavirus.DTO.users.CreateUserDTO;
import com.coranavirus.coronavirus.DTO.users.UserResponse;
import com.coranavirus.coronavirus.entity.Users;
import com.coranavirus.coronavirus.repository.UserRepository;
import com.coranavirus.coronavirus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isExistEmail(String email) {
        return userRepository.findOneByEmail(email).isPresent();
    }

    @Override
    public UserResponse createUser(CreateUserDTO createUserDTO) {
        Users user = new Users();
        user.setEmail(createUserDTO.getEmail());
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return mapUserToDTO(userRepository.save(user));
    }

    public UserResponse mapUserToDTO(Users users) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(users.getUserId());
        userResponse.setEmail(users.getEmail());
        userResponse.setCreatedAt(users.getCreatedAt());
        userResponse.setUpdatedAt(users.getUpdatedAt());
        return userResponse;
    }
}
