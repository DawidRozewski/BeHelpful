package pl.coderslab.charity.entity;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.validator.UniqueEmail;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Email
    @UniqueEmail
    private String email;

    @NotBlank
    private String password;

    @Transient
    @NotBlank
    private String repassword;

    private String verificationToken;

    private boolean enabled;

    private final String ROLE = "ROLE_USER";




}
