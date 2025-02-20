package com.crypto.bot.controller;

import com.crypto.bot.model.CoinBasePriceResponse;
import com.crypto.bot.service.CoinbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/coin/price/v2")
public class PriceController {

    @Autowired
    private CoinbaseService coinbaseService;

    @GetMapping(value = "/{name}")
    public Mono<CoinBasePriceResponse> getPrice(@PathVariable String name) {

        return coinbaseService.getCryptoPrice(name);
    }
}
