package com.example.currencyexchangeapp.service;


import com.example.currencyexchangeapp.controller.dto.CurrencyDto;
import com.example.currencyexchangeapp.controller.mapper.CurrencyDtoMapper;
import com.example.currencyexchangeapp.model.Currency;
import com.example.currencyexchangeapp.repository.CurrencyRepository;
import com.example.currencyexchangeapp.service.exception.CurrencyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
