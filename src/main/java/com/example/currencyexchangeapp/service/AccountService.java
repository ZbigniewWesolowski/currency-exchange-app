package com.example.currencyexchangeapp.service;

import com.example.currencyexchangeapp.controller.dto.AccountDto;
import com.example.currencyexchangeapp.controller.dto.CreateAccountDto;
import com.example.currencyexchangeapp.controller.mapper.AccountDtoMapper;
import com.example.currencyexchangeapp.model.Account;
import com.example.currencyexchangeapp.repository.AccountRepository;
import com.example.currencyexchangeapp.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountDtoMapper accountDtoMapper;

    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(account -> accountDtoMapper.mappingtoDto(account))
                .collect(Collectors.toList());
    }

    public AccountDto findAccountById(Integer id) throws AccountNotFoundException {
        return accountRepository.findById(id).map(account -> accountDtoMapper.mappingtoDto(account))
                .orElseThrow(() -> new AccountNotFoundException());

    }

    public Account getAccountById(Integer id) throws AccountNotFoundException {
        return accountRepository.findById(id)
                .orElseThrow(AccountNotFoundException::new);
    }

// TODO dodać transactional do tego żeby skasować transakcje z tym kontem bo będzie lecieć NULL!!

    public AccountDto deleteById(Integer id) throws AccountNotFoundException {
        Account account = getAccountById(id);
        accountRepository.delete(account);
        return accountDtoMapper.mappingtoDto(account);
    }

    public AccountDto createAccount(CreateAccountDto createAccountDto) throws AccountDataInvalidException, AccountDataInvalidException, AccountAlreadyExistException, UserNotFoundException, CurrencyNotFoundException {
        if (createAccountDto.getName() == null || createAccountDto.getNumber().length() < 7) {
            throw new AccountDataInvalidException();
        }
        if (accountRepository.existsById(createAccountDto.getId())) {
            throw new AccountAlreadyExistException();
        }
        Account newAccount = accountDtoMapper.mappingToModel(createAccountDto);
        newAccount.setBalance(BigDecimal.ZERO);
        newAccount.setCreatedAt(OffsetDateTime.now());
        newAccount.setUpdatedAt(OffsetDateTime.now());
        Account savedAccount = accountRepository.save(newAccount);

        return accountDtoMapper.mappingtoDto(savedAccount);
    }


}
