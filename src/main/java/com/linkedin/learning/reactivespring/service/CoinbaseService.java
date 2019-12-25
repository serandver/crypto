package com.linkedin.learning.reactivespring.service;

import com.linkedin.learning.reactivespring.model.CoinBasePriceResponse;
import com.linkedin.learning.reactivespring.model.CoinBasePurchaseResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CoinbaseService {

    Mono<CoinBasePriceResponse> getCryptoPrice(String priceName);

    Mono<CoinBasePurchaseResponse> createPurchase(String priceName);

    Mono<CoinBasePurchaseResponse> getPurchaseById(String id);

    Flux<CoinBasePurchaseResponse> listAllPurchases();
}
