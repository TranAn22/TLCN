package com.ecommerce.library.library.service.impl;

import com.ecommerce.library.library.model.Order;
import com.ecommerce.library.library.model.OrderDetail;
import com.ecommerce.library.library.repository.OrderDetailRepository;
import com.ecommerce.library.library.repository.OrderRepository;
import com.ecommerce.library.library.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderDetail> findAllByOrderId(long id) {
        return orderDetailRepository.getOrderDetailByOrderId(id);
    }

    @Override
    public List<OrderDetail> findAllOrderDeatails() {
        return orderDetailRepository.findAll();
    }
}
