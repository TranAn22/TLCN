package com.ecommerce.library.library.repository;

import com.ecommerce.library.library.model.OrderDetail;
import com.ecommerce.library.library.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query(value = "select * from order_detail o inner join orders p on o.order_id = p.order_id where p.order_id = ?1", nativeQuery = true)
    List<OrderDetail> getOrderDetailByOrderId(Long orderId);
}
