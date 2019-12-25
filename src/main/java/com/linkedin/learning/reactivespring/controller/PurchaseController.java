package com.linkedin.learning.reactivespring.controller;

import com.linkedin.learning.reactivespring.model.CoinBasePurchaseResponse;
import com.linkedin.learning.reactivespring.service.CoinbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/coin/purchase/v1")
public class PurchaseController {

    @Autowired
    private CoinbaseService coinbaseService;

    @PostMapping(value = "/{name}")
    public Mono<CoinBasePurchaseResponse> createPurchase(@PathVariable("name") String name) {
        return coinbaseService.createPurchase(name);
    }
}
