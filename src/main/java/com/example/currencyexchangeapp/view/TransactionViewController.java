package com.example.currencyexchangeapp.view;

import com.example.currencyexchangeapp.controller.dto.AccountDto;
import com.example.currencyexchangeapp.controller.dto.CreateTransactionDto;
import com.example.currencyexchangeapp.controller.dto.CurrencyDto;
import com.example.currencyexchangeapp.controller.dto.TransactionDto;
import com.example.currencyexchangeapp.service.AccountService;
import com.example.currencyexchangeapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class TransactionViewController {

    @Autowired
    TransactionService transactionService;
    AccountService accountService;

    @GetMapping("/transaction-table")
    public ModelAndView displayTransactions() {
        List<TransactionDto> allTransactions = transactionService.getAllTransactionsDtos();
        ModelAndView modelAndView = new ModelAndView("transactions-table");
        modelAndView.addObject("transactions", allTransactions);
        return modelAndView;
    }

    @GetMapping("create-transaction")
    public ModelAndView displayCreateTransactionForm() {
        CreateTransactionDto createTransactionDto = new CreateTransactionDto();
//        List<AccountDto> accountDtoList = accountService.getAllAccounts();
        ModelAndView modelAndView = new ModelAndView("create-transaction");
//        modelAndView.addObject("accountsListDto", accountDtoList);
        modelAndView.addObject("createDto", createTransactionDto);
        return modelAndView;
    }
}
