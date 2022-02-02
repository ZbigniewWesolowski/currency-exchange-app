package com.example.currencyexchangeapp.controller.mapper;

import com.example.currencyexchangeapp.controller.dto.TransactionDto;
import com.example.currencyexchangeapp.model.Account;
import com.example.currencyexchangeapp.model.Transaction;
import com.example.currencyexchangeapp.service.AccountService;
import com.example.currencyexchangeapp.service.TransactionService;
import com.example.currencyexchangeapp.service.UserService;
import com.example.currencyexchangeapp.service.exception.AccountNotFoundException;
import com.example.currencyexchangeapp.service.exception.TransactionNotFoundException;
import com.example.currencyexchangeapp.service.exception.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TransactionDtoMapper {
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;

    public TransactionDto mappingToDto(Transaction transaction) {
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .createdAt(transaction.getCreatedAt())
                .updatedAt(transaction.getUpdatedAt())
                .accountReciverId(transaction.getAccountReciver().getId())
                .accountSenderId(transaction.getAccountSender().getId())
                .build();
    }

    public Transaction mappingToModel(TransactionDto transactionDto) throws UserNotFoundException, AccountNotFoundException {
        return Transaction.builder()
                .id(transactionDto.getId())
                .amount(transactionDto.getAmount())
                .createdAt(transactionDto.getCreatedAt())
                .updatedAt(transactionDto.getUpdatedAt())
                .accountSender(accountService.getAccountById(transactionDto.getAccountSenderId()))
                .accountReciver(accountService.getAccountById(transactionDto.getAccountReciverId()))
                .build();
    }

}