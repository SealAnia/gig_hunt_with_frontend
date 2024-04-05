package com.example.gig_hunt.model.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
@DiscriminatorValue("A")
@ToString(callSuper = true, includeFieldNames = true)
public class Admin extends User {

    @Getter
    private static Long ADMIN_ROLE_ID = 1L;

    @Getter
    private static String ADMIN_FIRST_NAME = "admin";

    @Getter
    private static String ADMIN_SECOND_NAME = "admin";

    public static Long getADMIN_ROLE_ID() {
        return ADMIN_ROLE_ID;
    }

    public static String getADMIN_FIRST_NAME() {
        return ADMIN_FIRST_NAME;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Admin admin) {
        return super.equals(admin);
    }

}
