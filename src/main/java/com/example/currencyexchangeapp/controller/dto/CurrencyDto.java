package com.example.currencyexchangeapp.controller.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyDto {
    private Integer id;
    private String currencyName;
    private String code;
    private Double mid;
}