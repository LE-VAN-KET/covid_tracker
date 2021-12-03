package com.coranavirus.coronavirus.service;

import com.coranavirus.coronavirus.DTO.users.CreateUserDTO;
import com.coranavirus.coronavirus.DTO.users.UserResponse;

public interface UserService {
    boolean isExistEmail(String email);
    UserResponse createUser(CreateUserDTO createUserDTO);
}
