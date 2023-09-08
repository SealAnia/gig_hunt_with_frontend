package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("M")
@ToString(callSuper = true, includeFieldNames = true)
public class Master extends User {

    @ManyToOne
    @JoinColumn(name = "town_id")
    @ToString.Include
    private Town town;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Include
    private Category category;

    @Column(name = "maximum", columnDefinition = "number default = 0", nullable = false)
    @ToString.Include(name = "maximum_of_orders")
    private Integer maximum;

    @Column(name = "is_busy", columnDefinition = "tinyint default = 0")
    @ToString.Include
    private boolean isBusy;

    @Column(name = "active_orders", columnDefinition = "number default = 0")
    @ToString.Include
    private int activeOrders;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Include
    private Company company;

    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private List<Goods> goods;

    @Getter
    private static Long MASTER_ROLE_ID = 2L;

    @Getter
    private static final double AMOUNT_FREE_FROM_FEE = 170.0;

}
