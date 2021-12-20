package pl.coderslab.charity.entity;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.validator.UniqueEmail;

import javax.persistence.*;
import javax.validation.constraints.Email;

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

    private String password;

    @Transient
    private String repassword;

    private final String ROLE = "ROLE_USER";




}
