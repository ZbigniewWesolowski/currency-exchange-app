package com.example.currencyexchangeapp.service;


import com.example.currencyexchangeapp.controller.dto.CurrencyDto;
import com.example.currencyexchangeapp.controller.mapper.CurrencyDtoMapper;
import com.example.currencyexchangeapp.model.Currency;
import com.example.currencyexchangeapp.repository.CurrencyRepository;
import com.example.currencyexchangeapp.service.exception.CurrencyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    CurrencyDtoMapper currencyDtoMapper;

    public List<CurrencyDto> getAllCurrency () {
        return currencyRepository.findAll().stream()
                .map(currency -> currencyDtoMapper.mappingToDto(currency))
                .collect(Collectors.toList());
    }

    public CurrencyDto findById (Integer id) throws CurrencyNotFoundException {
        return currencyRepository.findById(id).map(currency -> currencyDtoMapper.mappingToDto(currency))
                .orElseThrow(CurrencyNotFoundException::new);
    }
    public Currency getById (Integer id) throws CurrencyNotFoundException {
        return currencyRepository.findById(id)
                .orElseThrow(CurrencyNotFoundException::new);
    }

    public String currencyIdTranslator (Integer id) {
        String currencyName = new String();
        currencyName = currencyRepository.getById(id).getCode();
        return currencyName;
    }

    public Integer currencyCodeTranslator (String code) {
        Integer currencyId;
        HashMap currenciesDictionary = new HashMap<String, Integer>();
        List <Currency> curriencies = new ArrayList<>();
        for (int i = 0; i < curriencies.size(); i++) {
            currenciesDictionary.put(curriencies.get(i).getCode(), curriencies.get(i).getId());
        }
        return currencyId = (Integer) currenciesDictionary.get(code);
        }
    }

