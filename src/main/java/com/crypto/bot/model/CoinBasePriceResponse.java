package com.crypto.bot.model;

import lombok.Data;

@Data
public class CoinBasePriceResponse {

    private Data data;

    @lombok.Data
    public static class Data {

        private String base;
        private String currency;
        private String amount;
    }
}
