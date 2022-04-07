package com.nhnacademy.bank.exception;

public class DifferentCurrencyException extends RuntimeException{

    public DifferentCurrencyException(String currencyName){
        super("DifferentCurrency"+currencyName);
    }
}
