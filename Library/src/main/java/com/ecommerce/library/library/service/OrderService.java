package com.ecommerce.library.library.service;

import com.ecommerce.library.library.model.Order;
import com.ecommerce.library.library.model.ShoppingCart;

public interface OrderService {
    void saveOrder(ShoppingCart cart, Order order);
    void acceptOrder(Long id);
    void cancelOrder(Long id);
}
