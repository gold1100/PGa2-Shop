package com.pawelgwozdz.shop.web;

import com.pawelgwozdz.shop.dto.AddOrderDTO;
import com.pawelgwozdz.shop.service.OrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping()
    public void createOrder(@Valid @RequestBody AddOrderDTO addOrderDTO) {
        ordersService.add(addOrderDTO);
    }
}
