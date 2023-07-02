package com.pawelgwozdz.shop.repository;

import com.pawelgwozdz.shop.entity.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductsRepository extends JpaRepository<OrderedProduct, Integer> {
}
