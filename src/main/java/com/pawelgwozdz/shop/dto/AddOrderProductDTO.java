package com.pawelgwozdz.shop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddOrderProductDTO {

    @NotNull
    private int id;

    @NotNull
    private int amount;
}
