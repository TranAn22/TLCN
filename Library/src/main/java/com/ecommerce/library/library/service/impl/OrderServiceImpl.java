package com.ecommerce.library.library.service.impl;

import com.ecommerce.library.library.model.CartItem;
import com.ecommerce.library.library.model.Order;
import com.ecommerce.library.library.model.OrderDetail;
import com.ecommerce.library.library.model.ShoppingCart;
import com.ecommerce.library.library.repository.CartItemRepository;
import com.ecommerce.library.library.repository.OrderDetailRepository;
import com.ecommerce.library.library.repository.OrderRepository;
import com.ecommerce.library.library.repository.ShoppingCartRepository;
import com.ecommerce.library.library.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShoppingCartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public void saveOrder(ShoppingCart cart,Order order) {
        Order order1=new Order();
        order1.setOrderStatus("PENDING");
        order1.setOrderDate(new Date());
        order1.setCustomer(cart.getCustomer());
        order1.setTotalPrice(cart.getTotalPrices());
        List<OrderDetail> orderDetailList=new ArrayList<>();
        for(CartItem item : cart.getCartItem()){
            OrderDetail orderDetail=new OrderDetail();
            orderDetail.setOrder(order1);
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setProduct(item.getProduct());
            orderDetail.setUnitPrice(item.getProduct().getCostPrice());
            orderDetailRepository.save(orderDetail);
            orderDetailList.add(orderDetail);
            cartItemRepository.delete(item);
        }
        order1.setOrderDetailList(orderDetailList);
        cart.setCartItem(new HashSet<>());
        cart.setTotalItems(0);
        cart.setTotalPrices(0);
        order1.setCity(order.getCity());
        order1.setDistrict(order.getDistrict());
        order1.setWard(order.getWard());
        order1.setAddress(order.getAddress());
        cartRepository.save(cart);
        orderRepository.save(order1);
    }

    @Override
    public void acceptOrder(Long id) {
        Order order=orderRepository.getById(id);
        order.setDeliveryDate(new Date());
        order.setOrderStatus("SHIPPING");
        orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
