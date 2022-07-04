package com.example.currencyexchangeapp.controller.mapper;

import com.example.currencyexchangeapp.controller.dto.CreateUserDto;
import com.example.currencyexchangeapp.controller.dto.UserDto;
import com.example.currencyexchangeapp.model.Account;
import com.example.currencyexchangeapp.model.User;

import com.example.currencyexchangeapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoMapper {

    @Autowired
    AccountRepository accountRepository;

    public UserDto mappingToDto (User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .ownedAccountId(user.getOwnedAccount().stream().map(account -> account.getId()).collect(Collectors.toList()))
                .build();
    }

    public User mappingToModel (UserDto userDto) {
        List<Account> accountList = accountRepository.findAllById(userDto.getOwnedAccountId().stream().collect(Collectors.toList()));

        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .createdAt(userDto.getCreatedAt())
                .updatedAt(userDto.getUpdatedAt())
                .ownedAccount(accountList)
                .build();
    }

    public User mappingToModel(CreateUserDto createUserDto) {
        return User.builder()
                .login(createUserDto.getLogin())
                .password(createUserDto.getPassword())
                .firstName(createUserDto.getFirstName())
                .lastName(createUserDto.getLastName())
                .email(createUserDto.getEmail())
                .build()    ;
    }
}