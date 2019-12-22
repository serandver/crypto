package com.linkedin.learning.reactivespring.controller;

import com.linkedin.learning.reactivespring.model.Purchase;
import com.linkedin.learning.reactivespring.service.CoinbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/coin/purchase/v2")
public class PurchaseController {

    @Autowired
    private CoinbaseService coinbaseService;

    @PostMapping(value = "/{name}")
    public Mono<Purchase> createPurchase(@PathVariable("name") String name) {
        return coinbaseService.createPurchase(name);
    }
}
