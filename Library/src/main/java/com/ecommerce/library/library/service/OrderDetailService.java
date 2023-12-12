package com.ecommerce.library.library.service;

import com.ecommerce.library.library.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> findAllByOrderId(long id);

    List<OrderDetail> findAllOrderDeatails();
}
