package com.example.currencyexchangeapp.controller.dto;

import com.example.currencyexchangeapp.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Integer currencyId;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private List<Integer> ownedAccountId = new ArrayList<>();
}
