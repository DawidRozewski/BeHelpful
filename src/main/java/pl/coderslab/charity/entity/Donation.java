package pl.coderslab.charity.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer quantity;

    @OneToMany
    List<Category> categories;

    @OneToOne
    Institution institution;

    String street;

    String city;

    String zipCode;

    LocalDate pickUpDate;

    LocalTime pickUpTime;

    String pickUpComment;

}
