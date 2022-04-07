package com.academy.bank;

import com.nhnacademy.bank.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static com.nhnacademy.bank.Currency.*;
import static org.assertj.core.api.Assertions.*;

public class BankTest {
    private ExchangeInfoModule module;
    private Bank bank;

    @BeforeEach
    void setUp(){
        module = mock(ExchangeInfoModule.class);
        bank = new Bank(module);
    }

    @DisplayName("은행을 통해서 환전할 수 있다.(1달러 <-> 1,000원)")
    @Test
    void exchange_dollarToWon() {
        Currency currency = WON;
        Money money = Money.dollar(1);

        ExchangeInfo info = new DollarExchangeInfo(currency, 1000);
        when (module.findExchangeInfo(DOLLAR)).thenReturn(info);

        Money result = bank.exchange(money, currency);
        assertThat(result).isEqualTo(Money.won(1000));

        verify(module).findExchangeInfo(DOLLAR);
    }

    @DisplayName("1,000원 -환전-> 1$")
    @Test
    void exchange_wonToDollar() {
        Currency beforeCurrency = WON;
        Currency afterCurrency = DOLLAR;
        Money money = Money.won(1000);

        ExchangeInfo info = new WonExchangeInfo(afterCurrency, 0.001);
        when (module.findExchangeInfo(beforeCurrency)).thenReturn(info);

        Money result = bank.exchange(money, afterCurrency);
        assertThat(result).isEqualTo(Money.dollar(1));

        verify(module).findExchangeInfo(beforeCurrency);
    }

    @DisplayName("5.25$ -> 5,250원")
    @Test
    void exchange_decimalDollarToWon() {
        Currency beforeCurrency = DOLLAR;
        Currency afterCurrency = WON;
        Money money = Money.dollar(5.25);

        ExchangeInfo info = new DollarExchangeInfo(afterCurrency, 1000);
        when(module.findExchangeInfo(beforeCurrency)).thenReturn(info);

        Money result = bank.exchange(money, afterCurrency);
        assertThat(result).isEqualTo(Money.won(5250));

        verify(module).findExchangeInfo(beforeCurrency);
    }


}
