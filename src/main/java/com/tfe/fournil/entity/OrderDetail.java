package com.tfe.fournil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name ="order_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_order_detail")
    private Long idOrderDetail;

    private  float price;
    private int quantity;

//    @ManyToOne(cascade=CascadeType.ALL)
//    @JoinColumn (name = "id_order", nullable = false)
//    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id_product")
    private Product product;
}
