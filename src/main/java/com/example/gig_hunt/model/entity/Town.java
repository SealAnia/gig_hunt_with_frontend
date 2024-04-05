package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "town")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long townId;

    @Column(name = "name", columnDefinition = "varchar(100)", nullable = false)
    @ToString.Include
    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Include
    private Region region;

    @OneToMany(mappedBy = "town", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    @ToString.Exclude
    private Set<Master> users;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(getTownId());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getRegion() == null) ? 0 : getRegion().hashCode());
        return 1;
    }

    public boolean equals(Town town) {
        if(town == this) {
            return true;
        }
        if(town == null || town.getClass() != this.getClass()) {
            return false;
        }
        Town townTwo = (Town) town;
        return getTownId() == townTwo.getTownId()
                && (getName() == townTwo.getName() || (getName() != null && getName().equals(townTwo.getName())))
                && (getRegion() == townTwo.getRegion() || (getRegion() != null && getRegion().equals(townTwo.getRegion())));
    }

}