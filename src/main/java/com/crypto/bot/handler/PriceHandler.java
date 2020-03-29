package com.crypto.bot.handler;

import com.crypto.bot.client.CoinbaseClient;
import com.crypto.bot.model.CoinBasePriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class PriceHandler {

    @Autowired
    private CoinbaseClient coinbaseClient;

    public Mono<ServerResponse> getPrice(ServerRequest serverRequest) {
        final Mono<CoinBasePriceResponse> price =
                coinbaseClient.getCryptoPrice(serverRequest.pathVariable("name"));

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(price, CoinBasePriceResponse.class);
    }
}
