package com.example.currencyexchangeapp.controller.mapper;

import com.example.currencyexchangeapp.controller.dto.AccountDto;
import com.example.currencyexchangeapp.controller.dto.CreateAccountDto;
import com.example.currencyexchangeapp.model.Account;
import com.example.currencyexchangeapp.service.CurrencyService;
import com.example.currencyexchangeapp.service.UserService;
import com.example.currencyexchangeapp.service.exception.AccountAlreadyExistException;
import com.example.currencyexchangeapp.service.exception.AccountDataInvalidException;
import com.example.currencyexchangeapp.service.exception.CurrencyNotFoundException;
import com.example.currencyexchangeapp.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AccountDtoMapper {

    @Autowired
    UserService userService;
    @Autowired
    CurrencyService currencyService;


    public AccountDto mappingtoDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .name(account.getName())
                .number(account.getNumber())
                .balance(account.getBalance())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
//                .userId(account.getOwner().getId())
                .currencyId(account.getCurrency().getId())
                .build();
    }

    public Account mappingToModel(AccountDto accountDto) throws UserNotFoundException, CurrencyNotFoundException {
        return Account.builder()
                .id(accountDto.getId())
                .name(accountDto.getName())
                .number(accountDto.getNumber())
                .balance(accountDto.getBalance())
                .createdAt(accountDto.getCreatedAt())
                .updatedAt(accountDto.getUpdatedAt())
                .owner(userService.findUserById(accountDto.getUserId()))
                .currency(currencyService.getById(accountDto.getCurrencyId()))
                .build();
    }

    public Account mappingToModel (CreateAccountDto createAccountDto) throws AccountAlreadyExistException, AccountDataInvalidException, UserNotFoundException, CurrencyNotFoundException {
        return Account.builder()
                .name(createAccountDto.getName())
                .currency(currencyService.getById(createAccountDto.getCurrencyId()))
                .number(createAccountDto.getNumber())
                .build();

    }
}