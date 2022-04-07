package com.nhnacademy.bank;

import com.nhnacademy.bank.exception.DifferentCurrencyException;
import static com.nhnacademy.bank.Currency.*;

public class Money {
    private final double amount;
    private final Currency currency;

    public Money(double amount, Currency currency) {
        if (amount < 0) {
            throw new IllegalArgumentException("Money is negative.");
        }
        this.amount = amount;
        this.currency = currency;
    }

    public static Money won(double amount) {
        return new Money(amount, WON);
    }

    public static Money dollar(double amount) {
        return new Money(amount, DOLLAR);
    }

    public double getAmount() {
        return amount;
    }

    public Money plus(Money money){
        if (!isSameCurrency(money)) {
            throw new DifferentCurrencyException("Currency is not same.");
        }
        return new Money(this.amount + money.getAmount(), this.currency);
    }

    public Money minus(Money money) {
        if (!isSameCurrency(money)) {
            throw new DifferentCurrencyException("Currency is not same.");
        }
        if (this.amount < money.getAmount()) {
            throw new IllegalArgumentException("Money is negative");
        }
        return new Money(this.amount - money.getAmount(), this.currency);
    }

    public boolean isSameCurrency(Money money) {
        return this.currency == money.getCurrency();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Money money = (Money) obj; //다운캐스팅
        return this.amount == money.getAmount() &&
            isSameCurrency(money);
    }

    public Currency getCurrency() {
        return currency;
    }
}
