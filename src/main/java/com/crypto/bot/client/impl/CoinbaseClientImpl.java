package com.crypto.bot.client.impl;

import com.crypto.bot.client.CoinbaseClient;
import com.crypto.bot.model.CoinBasePriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CoinbaseClientImpl implements CoinbaseClient {

    @Autowired
    private WebClient webClient;

    @Override
    public Mono<CoinBasePriceResponse> getCryptoPrice(String currencyName) {
        return webClient.get()
                .uri("https://api.coinbase.com/v2/prices/{crypto}/buy", currencyName)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(CoinBasePriceResponse.class));
    }
}
