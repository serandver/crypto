package com.crypto.bot.handler;

import com.crypto.bot.client.CoinbaseClient;
import com.crypto.bot.model.CoinBasePurchaseResponse;
import com.crypto.bot.service.CoinbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class PurchaseHandler {

    @Autowired
    private CoinbaseService coinbaseService;

    @Autowired
    private CoinbaseClient coinbaseClient;

    public Mono<ServerResponse> getPurchaseById(ServerRequest serverRequest) {
        final Mono<CoinBasePurchaseResponse> purchase =
                coinbaseService.getPurchaseById(serverRequest.pathVariable("id"));

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(purchase, CoinBasePurchaseResponse.class);
    }

    public Mono<ServerResponse> getAllPurchases(ServerRequest serverRequest) {
        final Flux<CoinBasePurchaseResponse> purchaseFlux = coinbaseService.getAllPurchases();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(purchaseFlux.collectList(), new ParameterizedTypeReference<List<CoinBasePurchaseResponse>>() {
                });
    }

    public Mono<ServerResponse> postPurchase(ServerRequest serverRequest) {
        Mono<CoinBasePurchaseResponse> purchaseResponse = coinbaseService.createPurchase(
                serverRequest.pathVariable("name")
        );

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(purchaseResponse, CoinBasePurchaseResponse.class);
    }
}
