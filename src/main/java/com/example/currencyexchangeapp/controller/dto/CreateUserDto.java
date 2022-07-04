package com.example.currencyexchangeapp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDto {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}

