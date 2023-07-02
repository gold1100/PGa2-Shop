package com.pawelgwozdz.shop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionMessage {
    NOT_ENOUGH_PRODUCTS("product.not_enough.title", "product.not_enough.name", "product.not_enough.reason"),
    PRODUCT_NOT_FOUND("product.not_found.title", "product.not_found.name", "product.not_found.reason"),
    CUSTOMER_NOT_FOUND("customer.not_found.title", "customer.not_found.name", "customer.not_found.reason");

    private final String title;
    private final String name;
    private final String reason;
}
