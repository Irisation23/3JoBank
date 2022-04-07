package com.nhnacademy.bank;

public class Bank {
    ExchangeInfoModule exchangeInfoModule;

    public Bank(ExchangeInfoModule module) {
        this.exchangeInfoModule = module;
    }

    public Money exchange(Money money, Currency currency) {
        ExchangeInfo exchangeInfo = exchangeInfoModule.findExchangeInfo(money.getCurrency());

        double amount = money.getAmount() * exchangeInfo.getRate();

        Money exchanged = new Money(amount, currency);

        return exchanged;
    }

}
