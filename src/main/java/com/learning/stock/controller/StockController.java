package com.learning.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RestController
@RequestMapping("/rest/stock")
public class StockController {

    @Autowired
    RestTemplate restTemplate;


    @RequestMapping(value = ("/{userName}") , method = RequestMethod.GET, produces = "application/json")
    public List<Stock> getStock(@PathVariable(value = "userName")String userName) throws Exception {

        ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://localhost:8300/rest/db/" + userName,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<String>>() {
                });

        List<String> quotes = quoteResponse.getBody();
       return quotes.stream()
                .map(this::getStockPrice)
                .collect(Collectors.toList());

    }

    private Stock getStockPrice(String quote) {
        try {
           return YahooFinance.get(quote);
        } catch (IOException e) {
            e.printStackTrace();
            return new Stock(quote);
        }
     }
}
