package com.linkedin.learning.reactivespring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class CoinBasePurchaseResponse {

    private final String name;
    private final String price;
    private final LocalDateTime createdAt;

    @Id
    private String id;

    public CoinBasePurchaseResponse(String name, String price, LocalDateTime createdAt) {
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
    }
}
