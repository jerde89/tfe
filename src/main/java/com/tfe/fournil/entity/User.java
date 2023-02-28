package com.tfe.fournil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_user")
    private Long IdUser;


    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @NotBlank(message = "le nom doit comporter au moins 1 caractère")
    @Size(max = 50, message = "le nom doit comporter maximun 50 caractères")
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;

    @NotBlank(message = "le prénom doit comporter au moins 1 caractère")
    @Size(max = 50, message = "le prénom doit comporter maximun 50 caractères")
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;

    @NotBlank(message = "l'email doit comporter au moins 1 caractère")
    @Size(max = 100, message = "l'email doit comporter maximun 100 caractères")
    @Column(name = "email", nullable = false, unique = true,length = 100)
    private String email;

    @NotBlank(message = "le téléphone doit comporter au moins 1 caractère")
    @Size(max = 50, message = "le téléphone doit comporter maximun 50 caractères")
    @Column(name = "phone", nullable = false, length = 50)
    private String phone;

//    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth", nullable = true)
    private LocalDate dateOfBirth;

    @NotBlank(message = "le mot de passe doit comporter au moins 1 caractère")

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled=true;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn (name = "id_address")
    private Address address;

}
