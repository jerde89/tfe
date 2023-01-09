package com.tfe.fournil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "Feedback")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "feedback_id")
    private long feedbackId;

    @NotBlank (message = "le prénom de l'utilisateur du formulaire de contact doit comporter au moins 1 caractère")
    @Size(max = 50, message = "le prénom de l'utilisateur du formulaire de contact doit comporter maximun 50 caractères")
    @Column(name = "firstname_feedback", nullable = false, length = 50)
    private String firstnameFeedback;

    @NotBlank (message = "le nom de l'utilisateur du formulaire de contact doit comporter au moins 1 caractère")
    @Size(max = 50, message = "le nom de l'utilisateur du formulaire de contact doit comporter maximun 50 caractères")
    @Column(name = "name_feedback", nullable = false, length = 50)
    private String nameFeedback;

    @NotBlank (message = "l'email de l'utilisateur du formulaire de contact doit comporter au moins 1 caractère")
    @Size(max = 50, message = "l'email de l'utilisateur du formulaire de contact doit comporter maximun 50 caractères")
    @Email (message = "l'email de formulaire de contact doit être un pattern.")
    @Column(name = "email_feedback", nullable = false, length = 50)
    private String emailFeedback;

    @NotBlank (message = "le téléphone de l'utilisateur du formulaire de contact doit comporter au moins 1 caractère")
    @Size(max = 15, message = "le téléphone de l'utilisateur du formulaire de contact doit comporter maximun 50 caractères")
    @Column(name = "phone_feedback", nullable = false, length = 15)
    private String phoneFeedback;

    @NotBlank (message = "le commentaire de l'utilisateur du formulaire de contact doit comporter au moins 1 caractère")
    @Size(max = 500, message = "le commentaire de l'utilisateur du formulaire de contact doit comporter maximun 500 caractères")
    @Column(name = "comment_feedback", nullable = false, length = 500)
    private String commentFeedback;

    @Column(name = "statut_feedback", nullable = false)
    private Boolean statutFeedback=false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at_feedback", nullable = false)
    private Date createdAtFeedback=new Date(System.currentTimeMillis());

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at_feedback", nullable = true)
    private Date updateAtFeedback;

    private String commentShort;
}