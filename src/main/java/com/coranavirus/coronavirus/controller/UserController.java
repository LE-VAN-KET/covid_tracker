package com.coranavirus.coronavirus.controller;

import com.coranavirus.coronavirus.DTO.users.CreateUserDTO;
import com.coranavirus.coronavirus.DTO.users.UserResponse;
import com.coranavirus.coronavirus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> registerEmail(@Valid @RequestBody CreateUserDTO createUserDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(createUserDTO));
    }
}
