package com.pawelgwozdz.shop.dto;

import com.pawelgwozdz.shop.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangeOrderStatusDTO {

    @NotNull
    private int orderId;

    @NotNull
    private OrderStatus orderStatus;
}
