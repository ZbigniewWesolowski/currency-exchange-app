package com.example.currencyexchangeapp.view;

import com.example.currencyexchangeapp.controller.dto.AccountDto;
import com.example.currencyexchangeapp.controller.dto.CreateAccountDto;
import com.example.currencyexchangeapp.service.AccountService;
import com.example.currencyexchangeapp.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountsViewController {

    @Autowired
    AccountService accountService;

    @GetMapping("/accounts-table")
    public ModelAndView displayAccountsTable () {
        ModelAndView modelAndView = new ModelAndView("accounts-table");
        List<AccountDto> accountsDtos = new ArrayList<>();
        accountsDtos = accountService.getAllAccounts();
        modelAndView.addObject("accounts", accountsDtos);
        return modelAndView;
    }

    @GetMapping ("delete-account")
    public ModelAndView deleteAccount(@RequestParam Integer id) throws AccountNotFoundException {
        accountService.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/accounts-table");
        return modelAndView;
    }

    @GetMapping ("details-account")
    public ModelAndView detailsAccount(@RequestParam Integer id) throws AccountNotFoundException {
        accountService.getAccountById(id);
        ModelAndView mav = new ModelAndView("redirect:/account-details");
        return mav;
    }

    @GetMapping ("create-account")
    public ModelAndView displayAccountForm (@ModelAttribute(name = "createDto")CreateAccountDto createAccountDto) throws AccountAlreadyExistException, AccountDataInvalidException {

        ModelAndView modelAndView = new ModelAndView("create-account");
        modelAndView.addObject("createAccountDto", new CreateAccountDto());
        return modelAndView;
    }

    @PostMapping ("create-account")
    public ModelAndView createAccount(@ModelAttribute ("createDto") CreateAccountDto createAccountDto) throws AccountAlreadyExistException, AccountDataInvalidException, UserNotFoundException, CurrencyNotFoundException {
        accountService.createAccount(createAccountDto);
        return new ModelAndView("redirect:/accounts-table");

    }
}
