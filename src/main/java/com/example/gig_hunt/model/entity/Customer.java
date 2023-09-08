package com.example.gig_hunt.model.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

}
