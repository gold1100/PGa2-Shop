package com.pawelgwozdz.shop.service;

import com.pawelgwozdz.shop.dto.AddOrderDTO;
import com.pawelgwozdz.shop.dto.ChangeOrderStatusDTO;

public interface OrdersService {

    void add(AddOrderDTO addOrderDTO);

    void changeStatus(ChangeOrderStatusDTO changeOrderStatusDTO);

}
