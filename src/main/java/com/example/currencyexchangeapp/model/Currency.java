package com.example.currencyexchangeapp.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String currency;
    private String code;
    private double mid;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
//    @OneToMany(mappedBy = "currency")
//    @Builder.Default
//    private List<Account> accountIds = new ArrayList<>();

}
