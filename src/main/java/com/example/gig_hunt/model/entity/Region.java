package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "region")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long regionId;

    @Column(name = "name", columnDefinition = "varchar(100)", nullable = false, unique = true)
    @ToString.Include
    private String name;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ToString.Exclude
    private Set<Town> towns;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(getRegionId());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }

    public boolean equals(Region region) {
        if(region == this) {
            return true;
        }
        if(region == null || region.getClass() != this.getClass()) {
            return false;
        }
        Region regionTwo = (Region) region;
        return getRegionId() == region.getRegionId()
                && (getName() == regionTwo.getName() || (getName() != null && getName().equals(regionTwo.getName())));
    }

}
