package com.crypto.bot.service.impl;

import com.crypto.bot.client.CoinbaseClient;
import com.crypto.bot.model.CoinBasePriceResponse;
import com.crypto.bot.model.CoinBasePurchaseResponse;
import com.crypto.bot.service.CoinbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class CoinbaseServiceImpl implements CoinbaseService {

    @Autowired
    private CoinbaseClient coinbaseClient;

    @Autowired
    private ReactiveMongoOperations reactiveMongoTemplate;

    @Override
    public Mono<CoinBasePurchaseResponse> createPurchase(String priceName) {
        return coinbaseClient.getCryptoPrice(priceName)
                .flatMap(price -> reactiveMongoTemplate.save(
                        new CoinBasePurchaseResponse(priceName, price.getData().getAmount(), LocalDateTime.now())
                ));
    }

    @Override
    public Mono<CoinBasePurchaseResponse> getPurchaseById(String id) {
        return reactiveMongoTemplate.findById(id, CoinBasePurchaseResponse.class);
    }

    @Override
    public Flux<CoinBasePurchaseResponse> listAllPurchases() {
        return reactiveMongoTemplate.findAll(CoinBasePurchaseResponse.class);
    }

    @Override
    public Mono<CoinBasePriceResponse> getCryptoPrice(String currencyName) {
        return coinbaseClient.getCryptoPrice(currencyName);
    }
}
