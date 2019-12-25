package com.linkedin.learning.reactivespring.service;

import com.linkedin.learning.reactivespring.model.CoinBasePriceResponse;
import com.linkedin.learning.reactivespring.model.CoinBasePurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class CoinbaseServiceImpl implements CoinbaseService {

    @Autowired
    private WebClient webClient;

    @Autowired
    private ReactiveMongoOperations reactiveMongoTemplate;

    @Override
    public Mono<CoinBasePriceResponse> getCryptoPrice(String priceName) {
        return webClient.get()
                .uri("https://api.coinbase.com/v2/prices/{crypto}/buy", priceName)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(CoinBasePriceResponse.class));
    }

    @Override
    public Mono<CoinBasePurchaseResponse> createPurchase(String priceName) {
        //Get crypto price from the coinbase API -> then save a mongoDB document containing the price
        return getCryptoPrice(priceName).flatMap(price -> reactiveMongoTemplate.save(
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
}
