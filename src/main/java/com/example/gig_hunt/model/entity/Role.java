package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long roleId;

    @Column(name = "name", columnDefinition = "varchar(100)", nullable = false)
    @ToString.Include
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    //private List<User> users;
    private Set<User> users;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(getRoleId());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }

    public boolean equals(Role role) {
        if(role == this) {
            return true;
        }
        if(role == null || role.getClass() != this.getClass()) {
            return false;
        }
        Role roleTwo = (Role) role;
        return getRoleId() == roleTwo.getRoleId()
                && (getName() == roleTwo.getName() || (getName() != null && getName().equals(roleTwo.getName())));
    }

}
