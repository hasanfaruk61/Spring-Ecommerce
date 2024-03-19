package com.laba.Spring.Ecommerce.odev7.controller;

import com.laba.Spring.Ecommerce.odev7.dto.request.CreateUserRequestDto;
import com.laba.Spring.Ecommerce.odev7.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        userService.saveUser(createUserRequestDto);
        return ResponseEntity.ok("User created successfully!");
    }
}
