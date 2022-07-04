package com.example.currencyexchangeapp.service;

import com.example.currencyexchangeapp.controller.dto.TransactionDto;
import com.example.currencyexchangeapp.controller.mapper.TransactionDtoMapper;
import com.example.currencyexchangeapp.model.Account;
import com.example.currencyexchangeapp.model.Transaction;
import com.example.currencyexchangeapp.repository.TransactionRepository;
import com.example.currencyexchangeapp.service.exception.AccountBalanceToLowException;
import com.example.currencyexchangeapp.service.exception.TransactionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionDtoMapper transactionDtoMapper;
    @Autowired
    AccountService accountService;

    public List<Transaction> getAllTransaction () {
        return transactionRepository.findAll();
    }

    public List<TransactionDto> getAllTransactionsDtos () {
        return transactionRepository.findAll().stream()
                .map(transactionDtoMapper::mappingToDto)
                .collect(Collectors.toList());
    }
    public Transaction findTransactionById (Integer id) throws TransactionNotFoundException {
        return transactionRepository.findById(id)
                .orElseThrow(TransactionNotFoundException::new);
    }

    public TransactionDto getTransactionById (Integer id) throws TransactionNotFoundException {
        return transactionRepository.findById(id).map(transaction -> transactionDtoMapper.mappingToDto(transaction))
                .orElseThrow(TransactionNotFoundException::new);
    }

    public boolean makeTransaction (Integer id) throws TransactionNotFoundException, AccountBalanceToLowException {
        Transaction transaction = findTransactionById(id);
        Account reciverAccount = transaction.getAccountReciver();
        Account senderAccount = transaction.getAccountSender();
        BigDecimal senderBalance = senderAccount.getBalance();
        BigDecimal reciverBalance = reciverAccount.getBalance();
        Double senderCurrencyMid = senderAccount.getCurrency().getMid();
        Double reciverCurrencyMid = reciverAccount.getCurrency().getMid();
        Double senderBalanceInPln = senderBalance.doubleValue() * senderCurrencyMid;
        Double reciverBalanceInPln = reciverBalance.doubleValue() * reciverCurrencyMid;
        Double transactionBalanceInPln = transaction.getAmount().doubleValue() * senderCurrencyMid;

        // zawsze mid z konta nadawczego!
        if (senderBalanceInPln >= transactionBalanceInPln) {
            reciverBalanceInPln += transactionBalanceInPln;
            senderBalanceInPln = senderBalanceInPln - transactionBalanceInPln;
            senderAccount.setBalance(BigDecimal.valueOf(senderBalanceInPln/senderCurrencyMid));
            reciverAccount.setBalance(BigDecimal.valueOf(reciverBalanceInPln/reciverCurrencyMid));
        }
        else {
            throw new AccountBalanceToLowException();
        }
    return true;
    }

}
