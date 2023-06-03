package com.tfe.fournil.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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
    private float price;

    @Column(name = "tax_rate", nullable = false, length = 2)
    private int taxRate;

    @ManyToOne
    @JoinColumn (name = "id_product")
    @JsonIgnore
    private Product product;

}
