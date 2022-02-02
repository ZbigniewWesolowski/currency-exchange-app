package com.example.currencyexchangeapp.controller.mapper;


import com.example.currencyexchangeapp.controller.dto.CurrencyDto;
import com.example.currencyexchangeapp.model.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyDtoMapper {

    public CurrencyDto mappingToDto (Currency currency) {
        return CurrencyDto.builder()
                .code(currency.getCode())
                .mid(currency.getMid())
                .currencyName(currency.getName())
                .build();
    }

    public Currency mappingToModel (CurrencyDto currencyDto) {
        return Currency.builder()
                .code(currencyDto.getCode())
                .mid(currencyDto.getMid())
                .name(currencyDto.getCurrencyName())
                .build();
    }
}



