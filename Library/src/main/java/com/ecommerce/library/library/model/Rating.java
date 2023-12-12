package com.ecommerce.library.library.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="product_id", referencedColumnName = "product_id")
    private Product product;
    @Column(name="Rating")
    private int rate;
    @Column(name="Comment")
    private String comment;
    @Column(name="Date")
    private Date date;

}
