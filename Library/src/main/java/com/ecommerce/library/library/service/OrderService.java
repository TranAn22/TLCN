package com.ecommerce.library.library.service;

import com.ecommerce.library.library.model.Order;
import com.ecommerce.library.library.model.ShoppingCart;

import java.util.List;

public interface OrderService {
    void saveOrder(ShoppingCart cart, Order order);
    Order acceptOrder(Long id);
    void cancelOrder(Long id);

    List<Order> findAll(String username);

    List<Order> findAllOrders();
}
