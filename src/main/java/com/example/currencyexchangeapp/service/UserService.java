package com.example.currencyexchangeapp.service;

import com.example.currencyexchangeapp.controller.dto.CreateUserDto;
import com.example.currencyexchangeapp.controller.dto.UserDto;
import com.example.currencyexchangeapp.controller.mapper.UserDtoMapper;
import com.example.currencyexchangeapp.model.User;
import com.example.currencyexchangeapp.repository.UserRepository;
import com.example.currencyexchangeapp.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDtoMapper userDtoMapper;


    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> userDtoMapper.mappingToDto(user))
                .collect(Collectors.toList());
    }

    public UserDto findUserDtoById(Integer id) throws UserNotFoundException {
        return userRepository.findById(id)
                .map(user -> userDtoMapper.mappingToDto(user))
                .orElseThrow(() -> new UserNotFoundException());
    }

    public User findUserById(Integer id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());
    }

    public UserDto createUser(CreateUserDto createUserDto) {

        User user = userDtoMapper.mappingToModel(createUserDto);
        user.setCreatedAt(OffsetDateTime.now());

        User savedUser = userRepository.save(user);
        return userDtoMapper.mappingToDto(savedUser);
    }


}
