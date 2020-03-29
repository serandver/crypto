package com.crypto.bot.config;

import com.crypto.bot.handler.PriceHandler;
import com.crypto.bot.handler.PurchaseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@EnableWebFlux
public class WebRouter implements WebFluxConfigurer {

    @Bean
    public PurchaseHandler purchaseHandler() {
        return new PurchaseHandler();
    }

    @Bean
    public PriceHandler priceHandler() {
        return new PriceHandler();
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunctionPurchase(final PurchaseHandler purchaseHandler) {
        return RouterFunctions
                .route(GET("/coin/purchase/v2/{id}").and(accept(MediaType.APPLICATION_JSON)), purchaseHandler::listPurchases)
                .andRoute(GET("/coin/purchase/v2/"), purchaseHandler::listAllPurchases);
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunctionPrice(final PriceHandler priceHandler) {
        return RouterFunctions
                .route(GET("/coin/price/v2/"), priceHandler::getPrice);
    }
}
