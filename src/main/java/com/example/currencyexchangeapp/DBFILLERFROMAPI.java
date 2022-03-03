package com.example.currencyexchangeapp;

import com.example.currencyexchangeapp.model.Account;
import com.example.currencyexchangeapp.model.Currency;
import com.example.currencyexchangeapp.repository.CurrencyRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.OffsetDateTime;

@ Service
public class DBFILLERFROMAPI {
    @Autowired
    CurrencyRepository currencyRepository;
    public Currency save (Currency currency) {
        return currencyRepository.save(currency);
    }
    @EventListener(ApplicationReadyEvent.class)
    public void fetchData () throws IOException, InterruptedException {
        String CURRENCY_DATA_URL = "http://api.nbp.pl/api/exchangerates/tables/A/?format=json";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(CURRENCY_DATA_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String[] responseCuttedToRows = response.body().split("\\{");
        for (int i = 2; i < responseCuttedToRows.length; i++) {
            responseCuttedToRows[i] = "{" + responseCuttedToRows[i];
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);


        for (int i = 2; i < responseCuttedToRows.length - 1; i++) {
            Currency currency = mapper.readValue(responseCuttedToRows[i], Currency.class);
            currency.setCreatedAt(OffsetDateTime.now());
            save(currency);
        }

        System.out.println("DB updated");


    }

}
