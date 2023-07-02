package com.pawelgwozdz.shop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AddOrderDTO {

    @NotNull
    private Date orderDate;

    @NotNull
    private int customerId;

    @NotNull
    private List<AddOrderProductDTO> products;
}
