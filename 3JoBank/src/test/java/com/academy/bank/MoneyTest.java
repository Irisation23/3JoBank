package com.academy.bank;

import com.nhnacademy.bank.Money;
import com.nhnacademy.bank.exception.DifferentCurrencyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import static com.nhnacademy.bank.Currency.*;

class MoneyTest {
    private Money money;

    @BeforeEach
    void setUp() {
        money = Money.won(1_000L);
    }

    @DisplayName("1000원 + 2000원 = 3000원")
    @Test
    void plus(){
        Money addMoney = Money.won(2_000L);
        Money result = money.plus(addMoney);

        assertThat(result.getAmount()).isEqualTo(3_000L);
    }

    @DisplayName("다른 통화는 계산할 수 없다")
    @Test
    void plus_differentCurrency_throwDifferentCurrencyException() {
        Money addMoney = Money.dollar(2_000L);
        assertThatThrownBy(() -> money.plus(addMoney))
            .isInstanceOf(DifferentCurrencyException.class)
            .hasMessageContainingAll("Different");
    }

    @DisplayName("1000원 - 500원 = 500원")
    @Test
    void minus() {
        Money minusMoney = Money.won(500L);
        Money result = money.minus(minusMoney);

        assertEquals(500, result.getAmount());
    }

    @DisplayName("계산한 값은 음수일 수 없다.")
    @Test
    void minus_resultIsNegative_throwIllegalArgumentException() {
        Money biggerMoney = Money.won(2_000L);

        assertThatThrownBy(() -> money.minus(biggerMoney))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContainingAll("negative");
    }

    @DisplayName("다른 통화는 계산할 수 없다")
    @Test
    void minus_differentCurrency_throwDifferentCurrencyException() {
        Money minusMoney = Money.dollar(500L);
        assertThatThrownBy(() -> money.minus(minusMoney))
            .isInstanceOf(DifferentCurrencyException.class)
            .hasMessageContainingAll("Different");
    }

    @DisplayName("돈은 음수일 수 없다.")
    @Test
    void moneyIsNegative_throwIllegalArgumentException() {
        assertThatThrownBy(() -> new Money(-2_000L, WON))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContainingAll("negative");
    }

    @DisplayName("1000원 = 1000원")
    @Test
    void equals() {
        Money equalsMoney = Money.won(1_000L);

        assertEquals(money, equalsMoney);
    }

    @DisplayName("금액이 다르거나 통화가 다르면 동일하지 않다.")
    @Test
    void equals_notEquals() {
        Money differentAmountMoney = Money.won(2_000L);
        Money differentCurrencyMoney = Money.dollar(2_000L);

        assertNotEquals(money, differentAmountMoney);
        assertNotEquals(money, differentCurrencyMoney);
    }

    @DisplayName("5.25$ + 5.25$ = 10.50$")
    @Test
    void plus_decimal(){
        Money money1 = Money.dollar(5.25d);
        Money money2 = Money.dollar(5.25d);

        assertEquals(10.50, money1.plus(money2).getAmount());
    }
}