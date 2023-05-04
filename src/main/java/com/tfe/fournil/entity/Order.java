package com.tfe.fournil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;

@Entity
@Table(name ="order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_order")
    private long IdOrder;

    @Column(name = "order_date")
    private LocalDate order_date;

    @Column(name = "date_of_receipt")
    private LocalDate dateOfReceipt;

    @Column(name = "statut", nullable = false, length = 50)
    private String statut;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn (name = "id_shop")
    @Valid()
    private Shop idShop;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn (name = "id_user")
    @Valid()
    private User idUser;
}
