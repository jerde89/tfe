package com.tfe.fournil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name ="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_order")
    private Long idOrder;

    @Column(name = "order_date")
    private LocalDate order_date;

    @Column(name = "date_of_receipt")
    private LocalDate dateOfReceipt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private Status  status;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_mode", nullable = false, length = 50)
    private DeliveryMode  deliveryMode;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn (name = "id_shop")
    private Shop shop;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn (name = "id_user")
    private User user;

}
