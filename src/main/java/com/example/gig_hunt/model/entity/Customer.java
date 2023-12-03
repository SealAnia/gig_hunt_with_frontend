package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("C")
@ToString(callSuper = true, includeFieldNames = true)
public class Customer extends User {

    @Getter
    private static Long CUSTOMER_ROLE_ID = 3L;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Customer customer) {
        return super.equals(customer);
    }

}
