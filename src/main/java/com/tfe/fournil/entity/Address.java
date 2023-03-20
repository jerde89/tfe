package com.tfe.fournil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;



@Entity
@Table(name ="Address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_address")
    private long IdAddress;

    @NotBlank(message = "la rue doit comporter au moins 1 caractère",groups = MyPersonalData.class)
    @Size(max = 100, message = "la rue doit comporter maximun 100 caractères")
    @Column(name = "street", nullable = false, length = 100)
    private String street;

    @NotBlank(message = "la numéro doit comporter au moins 1 caractère",groups = MyPersonalData.class)
    @Size(max = 10, message = "la numéro doit comporter maximun 10 caractères")
    @Column(name = "number", nullable = false, length = 10)
    private String number;


    @Column(name = "box", length = 10)
    private String box;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn (name = "id_city")
    @Valid
    private City city;
}