package com.laba.Spring.Ecommerce.odev10.service;

import com.laba.Spring.Ecommerce.odev10.dto.request.CreateUserRequestDto;
import com.laba.Spring.Ecommerce.odev10.dto.request.UpdateUserRequestDto;
import com.laba.Spring.Ecommerce.odev10.dto.response.UserResponseDto;
import com.laba.Spring.Ecommerce.odev10.entity.Users;
import com.laba.Spring.Ecommerce.odev10.exception.GeneralException;
import com.laba.Spring.Ecommerce.odev10.repository.UserRepository;
import com.laba.Spring.Ecommerce.odev10.service.smsService.UserSmsService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserSmsService userSmsService;

    public UserService(UserRepository userRepository, UserSmsService userSmsService) {
        this.userRepository = userRepository;

        this.userSmsService = userSmsService;
    }

    @Transactional
    public void saveUser(CreateUserRequestDto createUserRequestDto) {
        Users users = new Users();
        users.setName(createUserRequestDto.getName());
        users.setLastName(createUserRequestDto.getLastName());
        users.setEmail(createUserRequestDto.getEmail());
        users.setPhoneNumber(createUserRequestDto.getPhoneNumber());
        users.setPremium(createUserRequestDto.isPremium());

        userRepository.save(users);
    }

    public Optional<Users> findUserById(Long id){
        return userRepository.findById(id);
    }

    public UserResponseDto getOneUserByUserId(Long userId) {
        Users user = userRepository.findById(userId).get();
        return toResponseDto(user);
    }


    @Transactional
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequestDto updateUserRequestDto) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new GeneralException("User not found with id: " + userId));

        user.setName(updateUserRequestDto.getName());
        user.setLastName(updateUserRequestDto.getLastName());
        user.setEmail(updateUserRequestDto.getEmail());
        user.setPhoneNumber(updateUserRequestDto.getPhoneNumber());
        user.setPremium(updateUserRequestDto.isPremium());

        userRepository.save(user);

        userSmsService.sendSmsUser(user);

        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setName(user.getName());
        responseDto.setLastName(user.getLastName());
        responseDto.setEmail(user.getEmail());
        responseDto.setPhoneNumber(user.getPhoneNumber());
        responseDto.setPremium(user.isPremium());

        return ResponseEntity.ok(responseDto);
    }

    public List<UserResponseDto> getAllUsers() {
        List<Users> users = (List<Users>) userRepository.findAll();
        return users.stream().map(this::toResponseDto).toList();
    }

    private UserResponseDto toResponseDto(Users user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setName(user.getName());
        responseDto.setLastName(user.getLastName());
        responseDto.setEmail(user.getEmail());
        responseDto.setPhoneNumber(user.getPhoneNumber());
        responseDto.setPremium(user.isPremium());
        return responseDto;
    }
}
