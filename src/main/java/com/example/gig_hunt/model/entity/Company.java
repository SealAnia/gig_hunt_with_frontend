package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long companyId;

    @Column(name = "name", columnDefinition = "varchar(225)", nullable = false)
    @ToString.Include
    private String name;

    @Column(name = "registration_number", columnDefinition = "bigint", nullable = false, unique = true, length = 10)
    @ToString.Include
    private Long registrationNumber;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ToString.Include
    private Master user;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(getCompanyId());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + Long.hashCode(getRegistrationNumber());
        result = prime * result + ((getUser() == null) ? 0 : getUser().hashCode());
        return result;
    }

    public boolean equals(Company company) {
        if(company == this) {
            return true;
        }
        if(company == null || company.getClass() != this.getClass()) {
            return false;
        }
        Company companyTwo = (Company) company;
        return getCompanyId() == companyTwo.getCompanyId()
                && (getName() == companyTwo.getName() || (getName() != null && getName().equals(companyTwo.getName())))
                && getRegistrationNumber() == companyTwo.getRegistrationNumber()
                && (getUser() == companyTwo.getUser() || (getUser() != null && getUser().equals(companyTwo.getUser())));
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Long registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Master getUser() {
        return user;
    }

    public void setUser(Master user) {
        this.user = user;
    }
}