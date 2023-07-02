package com.pawelgwozdz.shop.exceptions;

import com.pawelgwozdz.shop.enums.ExceptionMessage;

public class ProductNotFoundException extends RuntimeException{

    private final ExceptionMessage exceptionMessage;

    public ProductNotFoundException(ExceptionMessage exceptionMessage, int id) {
        super(exceptionMessage.getTitle() + " Product id: " + id);
        this.exceptionMessage = exceptionMessage;
    }
}
