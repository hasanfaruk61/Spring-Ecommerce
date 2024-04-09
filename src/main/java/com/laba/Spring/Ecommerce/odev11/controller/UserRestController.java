package com.laba.Spring.Ecommerce.odev11.controller;

import com.laba.Spring.Ecommerce.odev11.dto.request.CreateUserRequestDto;
import com.laba.Spring.Ecommerce.odev11.dto.request.UpdateUserRequestDto;
import com.laba.Spring.Ecommerce.odev11.dto.response.UserResponseDto;
import com.laba.Spring.Ecommerce.odev11.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getOneUserById(@PathVariable Long userId) {
        UserResponseDto responseDto = userService.getOneUserByUserId(userId);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequestDto updateUserRequestDto) {
        userService.updateUser(userId, updateUserRequestDto);
        return ResponseEntity.ok("User updated successfully!");
    }
}
