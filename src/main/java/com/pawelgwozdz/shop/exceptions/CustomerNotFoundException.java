package com.pawelgwozdz.shop.exceptions;

import com.pawelgwozdz.shop.enums.ExceptionMessage;

public class CustomerNotFoundException extends RuntimeException{

    private final ExceptionMessage exceptionMessage;

    public CustomerNotFoundException(ExceptionMessage exceptionMessage, int id) {
        super(exceptionMessage.getTitle() + " Customer id: " + id);
        this.exceptionMessage = exceptionMessage;
    }
}
