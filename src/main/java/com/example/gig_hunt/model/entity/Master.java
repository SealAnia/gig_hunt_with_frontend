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
//@ToString(callSuper = true, includeFieldNames = true)
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result + super.hashCode();
        result = prime * result + ((town == null) ? 0 : town.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((maximum == 0 ) ? 0 : maximum.hashCode());
        result = prime * result + Boolean.hashCode(isBusy);
        result = prime * result + ((activeOrders == 0) ? 0 : activeOrders);
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        return result;
    }

    public boolean equals(Master master) {
        if(master == this) {
            return true;
        }
        if(master == null || master.getClass() != this.getClass()) {
            return false;
        }
        Master masterTwo = (Master) this;
        return super.userId == masterTwo.userId &&
                (super.nickname == masterTwo.nickname || (super.nickname != null && super.nickname.equals(masterTwo.nickname))) &&
                (super.password == masterTwo.password || (super.password != null && super.password.equals(masterTwo.password))) &&
                (super.role == masterTwo.role || (super.role != null && super.role.equals(masterTwo.role))) &&
                (super.personalData == null || (super.personalData != null && super.personalData.equals(masterTwo.personalData))) &&
                (super.getCard() == null || (super.getCard() != null && super.getCard().equals(masterTwo.getCard()))) &&
                super.isAccountNonExpired() == masterTwo.isAccountNonExpired() &&
                super.isAccountNonLocked() == masterTwo.isAccountNonLocked() &&
                super.isCredentialsNonExpired() == masterTwo.isCredentialsNonExpired() &&
                super.isEnabled() == masterTwo.isEnabled() &&
                (town == masterTwo.town || town != null && town.equals(masterTwo.town)) &&
                (category == masterTwo.category || category != null && category.equals(masterTwo.category)) &&
                (maximum == masterTwo.maximum || maximum != 0 && maximum.equals(masterTwo.maximum)) &&
                isBusy == masterTwo.isBusy &&
                (activeOrders == masterTwo.activeOrders || activeOrders != 0 && activeOrders == masterTwo.activeOrders) &&
                (company == masterTwo.company || company != null && company.equals(masterTwo.company));
    }

}
