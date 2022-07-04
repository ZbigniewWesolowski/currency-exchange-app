package com.example.currencyexchangeapp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTransactionDto {


    private BigDecimal amount;
    private Integer sendingAccountId;
    private Integer reciverAccountId;


}
