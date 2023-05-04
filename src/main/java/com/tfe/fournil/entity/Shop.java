package com.tfe.fournil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;

@Entity
@Table(name = "shop")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_shop")
    private long idShop;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt=new Date(System.currentTimeMillis());

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn (name = "id_address")
    @Valid
    private Address address;
}
