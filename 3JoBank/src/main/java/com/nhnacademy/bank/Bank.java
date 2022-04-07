package com.nhnacademy.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Bank {
    ExchangeInfoModule exchangeInfoModule;

    public Bank(ExchangeInfoModule module) {
        this.exchangeInfoModule = module;
    }

    public Money exchange(Money money, Currency aftercurrency) {
        ExchangeInfo exchangeInfo = exchangeInfoModule.findExchangeInfo(money.getCurrency());
        double amount = money.getAmount() * exchangeInfo.getRate();

//        if (aftercurrency == Currency.WON) {
//            if (amount % 10 >= 5 ) {
//                amount = Math.round((amount + 5) / 10 * 10);
//            }
//        }
//        if (aftercurrency == Currency.DOLLAR) {
//            amount = (double)Math.round((amount) * 100) / 100;
//        }

        if (aftercurrency == Currency.WON) {
            BigDecimal decimalAmount = BigDecimal.valueOf(amount).setScale(0, RoundingMode.HALF_UP);
            amount = decimalAmount.setScale(-1, RoundingMode.HALF_UP).doubleValue();
        }
        if (aftercurrency == Currency.DOLLAR) {
            BigDecimal decimalAmount = BigDecimal.valueOf(amount);
            amount = decimalAmount.setScale(2, RoundingMode.HALF_UP).doubleValue();
        }

        return new Money(amount, aftercurrency);
    }

}
