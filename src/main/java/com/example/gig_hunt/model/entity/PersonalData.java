package com.example.gig_hunt.model.entity;

import jakarta.persistence.Embeddable;

@Embeddable
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
        result = prime * result + ((getFirstName() == null) ? 0 : getFirstName().hashCode());
        result = prime * result + ((getSecondName() == null ) ? 0 : getSecondName().hashCode());
        result = prime * result + Long.hashCode(getPhoneNumber());
        result = prime * result + Long.hashCode((getWatsappNumber()));
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getPostalCode() == null) ? 0 : getPostalCode().hashCode());
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
        return (getFirstName() == personalDataTwo.getFirstName() || getFirstName() != null && getFirstName().equals(personalDataTwo.getFirstName()))
                && (getSecondName() == personalDataTwo.getSecondName() || getSecondName() != null && getSecondName().equals(personalDataTwo.getSecondName()))
                && getPhoneNumber() == personalDataTwo.getPhoneNumber() || (getPhoneNumber() != null && getPhoneNumber().equals(personalDataTwo.getPhoneNumber()))
                && getWatsappNumber() == personalDataTwo.getWatsappNumber() || (getWatsappNumber() != null && getWatsappNumber().equals(personalDataTwo.getWatsappNumber()))
                && (getEmail() == personalDataTwo.getEmail() || getEmail() != null && getEmail().equals(personalDataTwo.getEmail()))
                && (getAddress() == personalDataTwo.getAddress() || getAddress() != null && getAddress().equals(personalDataTwo.getAddress()))
                && (getPostalCode() == personalDataTwo.getPostalCode() || getPostalCode() != null && getPostalCode().equals(personalDataTwo.getPostalCode()));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getWatsappNumber() {
        return watsappNumber;
    }

    public void setWatsappNumber(Long watsappNumber) {
        this.watsappNumber = watsappNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }
}