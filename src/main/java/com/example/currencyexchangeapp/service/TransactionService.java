package com.example.currencyexchangeapp.service;

import com.example.currencyexchangeapp.controller.dto.TransactionDto;
import com.example.currencyexchangeapp.controller.mapper.TransactionDtoMapper;
import com.example.currencyexchangeapp.model.Transaction;
import com.example.currencyexchangeapp.repository.TransactionRepository;
import com.example.currencyexchangeapp.service.exception.TransactionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionDtoMapper transactionDtoMapper;

    public List<Transaction> getAllTransaction () {
        return transactionRepository.findAll();
    }

    public Transaction findTransactionById (Integer id) throws TransactionNotFoundException {
        return transactionRepository.findById(id)
                .orElseThrow(TransactionNotFoundException::new);
    }

    public TransactionDto getTransactionById (Integer id) throws TransactionNotFoundException {
        return transactionRepository.findById(id).map(transaction -> transactionDtoMapper.mappingToDto(transaction))
                .orElseThrow(TransactionNotFoundException::new);
    }

}
