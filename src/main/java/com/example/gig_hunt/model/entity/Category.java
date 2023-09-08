package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long categoryId;

    @Column(name = "name", columnDefinition = "varchar(100)", nullable = false, unique = true)
    @ToString.Include
    private String name;

    @Column(name = "comment", columnDefinition = "varchar(225)", nullable = true)
    @ToString.Include
    private String comment;

    @Column(name = "available_online", columnDefinition = "tinyint", nullable = false)
    @ToString.Include
    private boolean isAvailableOnline;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    @ToString.Exclude
    private List<Master> users;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(categoryId);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((comment == null) ? 0 : comment.hashCode());
        result = prime * result + ((isAvailableOnline) ? 0 : 1);
        return result;
    }

    public boolean equals(Category category) {
        if(category == this) {
            return true;
        }
        if(category == null || category.getClass() != this.getClass()) {
            return false;
        }
        Category categoryTwo = (Category) category;
        return categoryId == categoryTwo.categoryId
                && (name == categoryTwo.name || (name != null && name.equals(categoryTwo.name)))
                && (comment == categoryTwo.comment || (comment != null && comment.equals(categoryTwo.comment)))
                && isAvailableOnline == categoryTwo.isAvailableOnline;
    }

}
