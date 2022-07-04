package com.example.currencyexchangeapp.controller.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CreateAccountDto {
    private String name;
    private String number;
    private Integer currencyId;

}

