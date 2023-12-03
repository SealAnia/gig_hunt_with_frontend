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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((secondName == null ) ? 0 : secondName.hashCode());
        result = prime * result + Long.hashCode(phoneNumber);
        result = prime * result + Long.hashCode((watsappNumber));
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
        return result;
    }

    public boolean equals(PersonalData personalData) {
        if(personalData == this) {
            return true;
        }
        if(personalData == null || personalData.getClass() != this.getClass()) {
            return false;
        }
        PersonalData personalDataTwo = (PersonalData) this;
        return (firstName == personalDataTwo.firstName || firstName != null && firstName.equals(personalDataTwo.firstName))
                && (secondName == personalDataTwo.secondName || secondName != null && secondName.equals(personalDataTwo.secondName))
                && phoneNumber == personalDataTwo.phoneNumber || (phoneNumber != null && phoneNumber.equals(personalDataTwo.phoneNumber))
                && watsappNumber == personalDataTwo.watsappNumber || (watsappNumber != null && watsappNumber.equals(personalDataTwo.watsappNumber))
                && (email == personalDataTwo.email || email != null && email.equals(personalDataTwo.email))
                && (address == personalDataTwo.address || address != null && address.equals(personalDataTwo.address))
                && (postalCode == personalDataTwo.postalCode || postalCode != null && postalCode.equals(personalDataTwo.postalCode));
    }

}