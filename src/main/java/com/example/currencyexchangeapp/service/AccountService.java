package com.example.currencyexchangeapp.service;

import com.example.currencyexchangeapp.controller.dto.AccountDto;
import com.example.currencyexchangeapp.controller.mapper.AccountDtoMapper;
import com.example.currencyexchangeapp.model.Account;
import com.example.currencyexchangeapp.repository.AccountRepository;
import com.example.currencyexchangeapp.service.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

@Autowired
    private AccountRepository accountRepository;
@Autowired
    private AccountDtoMapper accountDtoMapper;
public List<AccountDto> getAllAccounts () {
    return accountRepository.findAll().stream()
            .map(account -> accountDtoMapper.mappingtoDto(account))
            .collect(Collectors.toList());
}
public AccountDto findAccountById (Integer id) throws AccountNotFoundException {
    return accountRepository.findById(id).map(account -> accountDtoMapper.mappingtoDto(account))
            .orElseThrow(() -> new AccountNotFoundException());

}

public Account getAccountById (Integer id) throws AccountNotFoundException {
    return accountRepository.findById(id)
            .orElseThrow(AccountNotFoundException::new);
}



}
