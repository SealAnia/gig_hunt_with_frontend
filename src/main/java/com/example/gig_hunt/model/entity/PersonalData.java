package com.example.gig_hunt.model.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalData {

    private String firstName;

    private String secondName;

    private Long phoneNumber;

    private Long watsappNumber;

    private String email;

    private String address;

    private Integer postalCode;

}