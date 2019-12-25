package com.linkedin.learning.reactivespring.handler;

import com.linkedin.learning.reactivespring.model.CoinBasePriceResponse;
import com.linkedin.learning.reactivespring.service.CoinbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class PriceHandler {

    @Autowired
    private CoinbaseService coinbaseService;

    public Mono<ServerResponse> getPrice(ServerRequest serverRequest) {
        final Mono<CoinBasePriceResponse> price =
                coinbaseService.getCryptoPrice(serverRequest.pathVariable("name"));

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(price, CoinBasePriceResponse.class);
    }
}
