package com.pawelgwozdz.shop.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    ORDERED,
    PAID,
    SEND,
    COMPLETED
}
