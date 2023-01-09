package com.tfe.fournil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name ="Country")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_country")
    private long IdCountry;

    @NotBlank(message = "le nomm de la ville doit comporter au moins 1 caractère")
    @Size(max = 100, message = "le nom du produit doit comporter maximun 100 caractères")
    @Column(name = "country_name", nullable = false, length = 100)
    private String countryName;

}
