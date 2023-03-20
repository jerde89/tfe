package com.tfe.fournil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotBlank (message = "le nom de la ville doit comporter au moins 1 caractère",groups = MyPersonalData.class)
    @Size(max = 100, message = "le nom de la ville doit comporter maximun 100 caractères")
    @Column(name = "city_name", nullable = false, length = 100)
    private String cityName;


    @NotNull(message = "le code postal doit comporter au moins 1 caractère",groups = MyPersonalData.class)
    @Column(name = "postal_code" , nullable = false)
    private String postalCode;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn (name = "id_country")
    @Valid
    private Country country;
}
