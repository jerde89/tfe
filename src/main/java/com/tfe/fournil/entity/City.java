package com.tfe.fournil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table (name = "Cities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "city_id")
    private long id;

    @NotBlank
    @Column(name = "city_name", nullable = false, length = 100)
    private String cityName;

    @NotBlank
    @Column(name = "postal_code")
    private int postalCode;

}
