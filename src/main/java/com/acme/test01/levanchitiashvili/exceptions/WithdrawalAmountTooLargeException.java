package com.acme.test01.levanchitiashvili.exceptions;

public class WithdrawalAmountTooLargeException extends Exception{
    public WithdrawalAmountTooLargeException() {
        super("Withdrawal amount is too large");
    }

    public WithdrawalAmountTooLargeException(String message) {
        super(message);
    }
}
