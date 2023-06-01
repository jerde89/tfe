package com.tfe.fournil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
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

    @NotBlank (message = "Le prénom doit comporter au moins 1 caractère")
    @Size(max = 50, message = "Le prénom doit comporter maximun 50 caractères")
    @Column(name = "firstname_feedback", nullable = false, length = 50)
    private String firstnameFeedback;

    @NotBlank (message = "Le nom doit comporter au moins 1 caractère")
    @Size(max = 50, message = "Le nom doit comporter maximun 50 caractères")
    @Column(name = "name_feedback", nullable = false, length = 50)
    private String nameFeedback;

    @NotBlank (message = "L'email doit comporter au moins 1 caractère")
    @Size(max = 50, message = "L'email doit comporter maximun 100 caractères")
    @Email (message = "L'email de formulaire de contact doit être un pattern.")
    @Column(name = "email_feedback", nullable = false, length = 100)
    private String emailFeedback;

    @NotBlank (message = "Le téléphone doit comporter au moins 1 caractère")
    @Size(max = 15, message = "Le téléphone doit comporter maximun 15 caractères")
    @Column(name = "phone_feedback", nullable = false, length = 15)
    private String phoneFeedback;

    @NotBlank (message = "Le commentaire doit comporter au moins 1 caractère")
    @Size(max = 500, message = "Le commentaire doit comporter maximun 500 caractères")
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

//    private String commentShort;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn (name = "id_user")
    private User user;
}