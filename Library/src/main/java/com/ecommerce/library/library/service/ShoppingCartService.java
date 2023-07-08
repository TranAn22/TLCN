package com.ecommerce.library.library.service;

import com.ecommerce.library.library.model.Customer;
import com.ecommerce.library.library.model.Product;
import com.ecommerce.library.library.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart addItemToCart(Product product, int quantity, Customer customer);

    ShoppingCart updateItemInCart(Product product, int quantity, Customer customer);

    ShoppingCart deleteItemFromCart(Product product, Customer customer);

}