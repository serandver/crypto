package com.crypto.bot.client;

import com.crypto.bot.model.CoinBasePriceResponse;
import reactor.core.publisher.Mono;

public interface CoinbaseClient extends AbstractClient {
    Mono<CoinBasePriceResponse> getCryptoPrice(String currencyName);
}
