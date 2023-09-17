package com.acme.test01.levanchitiashvili.exceptions;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException() {
        super("account Not found by thi id");
    }

    public AccountNotFoundException(String message) {
        super(message);
    }
}
