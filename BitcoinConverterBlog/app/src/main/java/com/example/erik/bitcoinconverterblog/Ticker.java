package com.example.erik.bitcoinconverterblog;

import java.math.BigDecimal;

public class Ticker {
    private BigDecimal price;

    public Ticker() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

