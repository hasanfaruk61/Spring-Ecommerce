package com.laba.Spring.Ecommerce.odev7.service;

import com.laba.Spring.Ecommerce.odev7.dto.request.CreateUserRequestDto;
import com.laba.Spring.Ecommerce.odev7.entity.Users;
import com.laba.Spring.Ecommerce.odev7.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(CreateUserRequestDto createUserRequestDto) {
        Users users = new Users();
        users.setName(createUserRequestDto.getName());
        users.setLastName(createUserRequestDto.getLastName());
        users.setEmail(createUserRequestDto.getEmail());
        users.setPhoneNumber(createUserRequestDto.getPhoneNumber());

        userRepository.save(users);
    }

    public Optional<Users> findUserById(Long id){
        return userRepository.findById(id);
    }
}
