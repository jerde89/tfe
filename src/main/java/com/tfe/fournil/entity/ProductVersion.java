package com.tfe.fournil.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name ="ProductVersion")
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties(value = { "productVersion", "currentVersion" })
public class ProductVersion {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "begin_date", nullable = false)
    private LocalDate BeginDate;

    @Column(name = "end_date", nullable = true)
    private LocalDate EndDate;

    @Column(name = "price", nullable = false)
    private float Price;

    @Column(name = "tax_rate", nullable = false, length = 2)
    private int taxRate;

    @ManyToOne
    @JoinColumn (name = "id_product")
    private Product product;

}