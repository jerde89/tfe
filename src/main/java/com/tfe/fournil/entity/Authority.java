package com.tfe.fournil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "authorities", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "auth_id", nullable = false)
    private Long auth_id;

    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "authority")
    private String authority;



}

