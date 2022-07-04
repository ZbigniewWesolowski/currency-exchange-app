package com.example.currencyexchangeapp.controller.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {

    private Integer id;
    private BigDecimal amount;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private Integer accountSenderId;
    private Integer accountReciverId;


}
