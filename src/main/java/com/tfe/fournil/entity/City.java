package com.tfe.fournil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table (name = "City")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_city")
    private long idCity;

    @NotBlank (message = "le nom de la ville doit comporter au moins 1 caractère")
    @Size(max = 100, message = "le nom de la ville doit comporter maximun 100 caractères")
    @Column(name = "city_name", nullable = false, length = 100)
    private String cityName;


    @NotBlank (message = "le code postal doit comporter au moins 1 caractère")
    @Size(max = 10, message = "le code postal doit comporter maximun 10 caractères")
    @Column(name = "postal_code" , nullable = false, length = 10)
    private int postalCode;

    @ManyToOne
    @JoinColumn (name = "id_country")
    private Country country;
}
