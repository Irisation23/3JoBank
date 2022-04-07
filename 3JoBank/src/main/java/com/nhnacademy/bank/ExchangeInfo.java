package com.nhnacademy.bank;

public class ExchangeInfo {
    double rate;
    Currency currency;

    public ExchangeInfo(Currency currency, double rate) {
        this.rate = rate;
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getRate() {
        return rate;
    }
}
