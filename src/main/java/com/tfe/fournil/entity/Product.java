package com.tfe.fournil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;


@Entity
@Table(name ="Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_product")
    private long idProduct;

    @NotBlank (message = "le nom du produit doit comporter au moins 1 caractère")
    @Size(max = 50, message = "le nom du produit doit comporter maximun 50 caractères")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotBlank (message = "la description du produit produit doit comporter au moins 1 caractère")
    @Size(max = 200, message = "la description du produit doit comporter maximun 200 caractères")
    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "price", nullable = false, length = 10)
    private float price;

    @Column(name = "tax_rate", nullable = false, length = 2)
    private int taxRate;

    @Column(name = "Img", nullable = true)
    private String img;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt=new Date(System.currentTimeMillis());

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at", nullable = true)
    private Date updateAt;

    @Column(name = "enable", nullable = false, length = 500)
    private Boolean enable=false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "id_product_category")
    private ProductCategory category;
}
