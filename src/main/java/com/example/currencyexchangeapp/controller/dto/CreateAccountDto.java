package com.example.currencyexchangeapp.controller.dto;
import com.example.currencyexchangeapp.model.Currency;
import com.example.currencyexchangeapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CreateAccountDto {
    private String name;
    private BigDecimal balance;
    private String number;
    private Integer currencyId;

}

