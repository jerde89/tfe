package com.tfe.fournil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;


@Entity
@Table(name = "authority", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_authority", nullable = false)
    private long idAuthority;

    @Size(max = 50, message = "le nom de la ville doit comporter maximun 50 caractères")
    @Column(name = "authority_description", nullable = false, length = 50)
    private String authorityDescription;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn (name = "username")
    private User username;

}

