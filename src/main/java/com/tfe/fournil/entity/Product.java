package com.tfe.fournil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_product")
    private long Idproduct;

    @NotBlank (message = "le nom du produit doit comporter au moins 1 caractère")
    @Size(max = 50, message = "le nom du produit doit comporter maximun 50 caractères")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotBlank (message = "la description du produit produit doit comporter au moins 1 caractère")
    @Size(max = 10, message = "la description du produit doit comporter maximun 200 caractères")
    @Column(name = "description", nullable = false, length = 50)
    private String descriptionProduct;

    @NotBlank (message = "le prix du produit produit doit comporter au moins 1 caractère")
    @Size(max = 10, message = "le prix du produit doit comporter maximun 10 caractères")
    @Column(name = "price", nullable = false, length = 50)
    private float price;

    @NotBlank (message = "le taux de TVA du produit produit doit comporter ne peut pas être vide")
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

    @ManyToOne
    @JoinColumn (name = "id_product_category")
    private ProductCategory productCategory;
}
