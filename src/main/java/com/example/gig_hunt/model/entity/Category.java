package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

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
    private Set<Master> users;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(getCategoryId());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((isAvailableOnline()) ? 0 : 1);
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
        return getCategoryId() == categoryTwo.getCategoryId()
                && (getName() == categoryTwo.getName() || (getName() != null && getName().equals(categoryTwo.getName())))
                && (getComment() == categoryTwo.getComment() || (getComment() != null && getComment().equals(categoryTwo.getComment())))
                && isAvailableOnline() == categoryTwo.isAvailableOnline();
    }

}
