package com.example.currencyexchangeapp.view;


import com.example.currencyexchangeapp.controller.dto.CurrencyDto;
import com.example.currencyexchangeapp.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CurrencyViewController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping ("/currencies-table")
    public ModelAndView displayCurrencyTable () {
        ModelAndView modelAndView = new ModelAndView("currencies-table");
        List<CurrencyDto> currenciesDtoList = new ArrayList<>();
        currenciesDtoList = currencyService.getAllCurrency();
        modelAndView.addObject("currencies", currenciesDtoList);
        return modelAndView;
    }
}
