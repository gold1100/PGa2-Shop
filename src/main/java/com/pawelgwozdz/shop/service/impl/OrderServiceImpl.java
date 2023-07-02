package com.pawelgwozdz.shop.service.impl;

import com.pawelgwozdz.shop.dto.AddOrderDTO;
import com.pawelgwozdz.shop.dto.AddOrderProductDTO;
import com.pawelgwozdz.shop.dto.ChangeOrderStatusDTO;
import com.pawelgwozdz.shop.entity.Order;
import com.pawelgwozdz.shop.entity.OrderedProduct;
import com.pawelgwozdz.shop.entity.Product;
import com.pawelgwozdz.shop.enums.ExceptionMessage;
import com.pawelgwozdz.shop.enums.OrderStatus;
import com.pawelgwozdz.shop.exceptions.CustomerNotFoundException;
import com.pawelgwozdz.shop.exceptions.NotEnoughProductsException;
import com.pawelgwozdz.shop.exceptions.ProductNotFoundException;
import com.pawelgwozdz.shop.repository.CustomersRepository;
import com.pawelgwozdz.shop.repository.OrderedProductsRepository;
import com.pawelgwozdz.shop.repository.OrdersRepository;
import com.pawelgwozdz.shop.repository.ProductsRepository;
import com.pawelgwozdz.shop.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final ProductsRepository productsRepository;
    private final CustomersRepository customersRepository;
    private final OrderedProductsRepository orderedProductsRepository;

    @Override
    @Transactional
    public void add(AddOrderDTO addOrderDTO) {
        BigDecimal orderPrice = removeFromWarehouse(addOrderDTO.getProducts());
        create(addOrderDTO, orderPrice);
    }

    private void create(AddOrderDTO addOrderDTO, BigDecimal orderPrice) {
        Order order = Order.builder()
                .orderDate(addOrderDTO.getOrderDate())
                .customer(customersRepository.findById(addOrderDTO.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException(ExceptionMessage.CUSTOMER_NOT_FOUND, addOrderDTO.getCustomerId())))
                .price(orderPrice)
                .status(OrderStatus.ORDERED)
                .build();
        order = ordersRepository.save(order);
        addOrderedProducts(addOrderDTO.getProducts(), order);
    }

    private void addOrderedProducts(List<AddOrderProductDTO> products, Order order) {
        List<OrderedProduct> orderedProducts = new ArrayList<>();
        for (AddOrderProductDTO addOrderProductDTO : products) {
            IntStream.range(0, addOrderProductDTO.getAmount()).forEach( e -> {
                orderedProducts.add(OrderedProduct.builder()
                        .products(productsRepository.findById(addOrderProductDTO.getId()).orElseThrow(() -> new ProductNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND, addOrderProductDTO.getId())))
                        .order(order).build());
            });
        }
        orderedProductsRepository.saveAll(orderedProducts);
    }

    private BigDecimal removeFromWarehouse(List<AddOrderProductDTO> addOrderProductDTO) {

        BigDecimal orderPrice = new BigDecimal("0");
        List<Product> updatedProducts = new ArrayList<>();
        for (AddOrderProductDTO productDTO : addOrderProductDTO) {
            Product product = productsRepository.findById(productDTO.getId()).orElseThrow( () -> new ProductNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND, productDTO.getId()));
            if (product.getAmount() < productDTO.getAmount())
                throw new NotEnoughProductsException(ExceptionMessage.NOT_ENOUGH_PRODUCTS);

            product.setAmount(product.getAmount() - productDTO.getAmount());
            updatedProducts.add(product);
            orderPrice = orderPrice.add(product.getPrice().multiply(new BigDecimal(productDTO.getAmount())));
        }
        productsRepository.saveAll(updatedProducts);

        return orderPrice;
    }
    @Override
    public void changeStatus(ChangeOrderStatusDTO changeOrderStatusDTO) {

    }
}
