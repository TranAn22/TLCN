package com.ecommerce.library.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    private Date orderDate;
    private Date deliveryDate;
    private double totalPrice;
    private double shippingFee;
    private String orderStatus;
    @Column(name = "District")
    private String District;
    @Column(name = "Ward")
    private String Ward;
    @Column(name = "city")
    private String city;
    private String address;
    private String notes;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;
    private boolean isAccept;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderDetail> orderDetailList;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", totalPrice=" + totalPrice +
                ", shippingFee=" + shippingFee +
                ", orderStatus='" + orderStatus + '\'' +
                ", District='" + District + '\'' +
                ", Ward='" + Ward + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", notes='" + notes + '\'' +
                ", customer=" + customer +
                ", isAccept=" + isAccept +
                ", orderDetailList=" + orderDetailList +
                '}';
    }
}