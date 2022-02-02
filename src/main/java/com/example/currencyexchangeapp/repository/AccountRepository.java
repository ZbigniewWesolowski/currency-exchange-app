package com.example.currencyexchangeapp.repository;

import com.example.currencyexchangeapp.model.Account;
import com.example.currencyexchangeapp.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends  JpaRepository<Account, Integer>{
}
