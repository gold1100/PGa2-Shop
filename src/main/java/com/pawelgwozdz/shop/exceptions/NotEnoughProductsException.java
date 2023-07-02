package com.pawelgwozdz.shop.exceptions;

import com.pawelgwozdz.shop.enums.ExceptionMessage;

public class NotEnoughProductsException extends RuntimeException{

    private final ExceptionMessage exceptionMessage;

    public NotEnoughProductsException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getTitle());
        this.exceptionMessage = exceptionMessage;
    }
}
