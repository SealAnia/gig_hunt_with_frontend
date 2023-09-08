package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private List<Town> towns;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(regionId);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        return regionId == region.regionId
                && (name == regionTwo.name || (name != null && name.equals(regionTwo.name)));
    }

}
