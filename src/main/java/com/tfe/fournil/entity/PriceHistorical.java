package com.tfe.fournil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name ="PriceHistorical")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceHistorical {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "begin_date_hour", nullable = false)
    private Date BeginDateHour=new Date(System.currentTimeMillis());

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date_hour", nullable = false)
    private Date EndDateHour =new Date(System.currentTimeMillis());

    @NotBlank (message = "le prix du produit produit doit comporter au moins 1 caractère")
    @Size(max = 10, message = "le prix du produit doit comporter maximun 10 caractères")
    @Column(name = "price", nullable = false)
    private float Price;

    @ManyToOne
    @JoinColumn (name = "id_product")
    private Product product;

}