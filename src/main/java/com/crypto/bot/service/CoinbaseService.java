package com.crypto.bot.service;

import com.crypto.bot.model.CoinBasePriceResponse;
import com.crypto.bot.model.CoinBasePurchaseResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CoinbaseService extends AbstractService {
    Mono<CoinBasePurchaseResponse> createPurchase(String priceName);

    Mono<CoinBasePurchaseResponse> getPurchaseById(String id);

    Flux<CoinBasePurchaseResponse> getAllPurchases();

    Mono<CoinBasePriceResponse> getCryptoPrice(String currencyName);
}
