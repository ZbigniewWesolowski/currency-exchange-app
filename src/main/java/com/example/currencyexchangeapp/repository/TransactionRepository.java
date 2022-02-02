package com.example.currencyexchangeapp.repository;

import com.example.currencyexchangeapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository <Transaction, Integer> {
}