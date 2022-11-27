package com.tfe.fournil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name ="ProductCategory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_product_category")
    private long IdProductCategory;

    @NotBlank (message = "le nom de la catégorie doit comporter au moins 1 caractère")
    @Size(max = 50, message = "le nom de la catégorie doit comporter maximun 50 caractères")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotBlank (message = "la description de la categorie doit comporter au moins 1 caractère")
    @Size(max = 10, message = "la description du produit doit comporter maximun 200 caractères")
    @Column(name = "description", nullable = false, length = 50)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt=new Date(System.currentTimeMillis());

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at", nullable = true)
    private Date updateAt;

    @Column(name = "enable", nullable = false, length = 500)
    private Boolean enable=false;

}
