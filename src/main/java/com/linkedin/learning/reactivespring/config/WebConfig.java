package com.linkedin.learning.reactivespring.config;

import com.linkedin.learning.reactivespring.handler.PriceHandler;
import com.linkedin.learning.reactivespring.handler.PurchaseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
public class WebConfig implements WebFluxConfigurer {

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
                .route(RequestPredicates.GET("/coin/purchase/v2/{id}")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), purchaseHandler::listPurchases);
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunctionAllPurchases(final PurchaseHandler purchaseHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/coin/purchase/v2/"), purchaseHandler::listAllPurchases);
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunctionPrice(final PriceHandler priceHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/coin/price/v2/"), priceHandler::getPrice);
    }
}
