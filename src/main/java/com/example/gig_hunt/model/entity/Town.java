package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.Objects;

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
    private List<Master> users;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(townId);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        return townId == townTwo.townId
                && (name == townTwo.name || (name != null && name.equals(townTwo.name)));
    }

}