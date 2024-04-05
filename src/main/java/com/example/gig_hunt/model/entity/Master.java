package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("M")
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
    private Set<Goods> goods;

    @Getter
    private static Long MASTER_ROLE_ID = 2L;

    @Getter
    private static final double AMOUNT_FREE_FROM_FEE = 170.0;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result + super.hashCode();
        result = prime * result + ((getTown() == null) ? 0 : getTown().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getMaximum() == 0 ) ? 0 : getMaximum().hashCode());
        result = prime * result + Boolean.hashCode(isBusy());
        result = prime * result + ((getActiveOrders() == 0) ? 0 : getActiveOrders());
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
        return super.getUserId() == masterTwo.getUserId() &&
                (super.getNickname() == masterTwo.getNickname() || (super.getNickname() != null && super.getNickname().equals(masterTwo.getNickname()))) &&
                (super.getPassword() == masterTwo.getPassword() || (super.getPassword() != null && super.getPassword().equals(masterTwo.getPassword()))) &&
                (super.getRole() == masterTwo.getRole() || (super.getRole() != null && super.getRole().equals(masterTwo.getRole()))) &&
                (super.getPersonalData() == null || (super.getPersonalData() != null && super.getPersonalData().equals(masterTwo.getPersonalData()))) &&
                (super.getCard() == null || (super.getCard() != null && super.getCard().equals(masterTwo.getCard()))) &&
                super.isAccountNonExpired() == masterTwo.isAccountNonExpired() &&
                super.isAccountNonLocked() == masterTwo.isAccountNonLocked() &&
                super.isCredentialsNonExpired() == masterTwo.isCredentialsNonExpired() &&
                super.isEnabled() == masterTwo.isEnabled() &&
                (getTown() == masterTwo.getTown() || getTown() != null && getTown().equals(masterTwo.getTown())) &&
                (getCategory() == masterTwo.getCategory() || getCategory() != null && getCategory().equals(masterTwo.getCategory())) &&
                (getMaximum() == masterTwo.getMaximum() || getMaximum() != 0 && getMaximum().equals(masterTwo.getMaximum())) &&
                isBusy() == masterTwo.isBusy() &&
                (getActiveOrders() == masterTwo.getActiveOrders() || getActiveOrders() != 0 && getActiveOrders() == masterTwo.getActiveOrders()) &&
                (getCompany() == masterTwo.getCompany() || getCompany() != null && getCompany().equals(masterTwo.getCompany()));
    }

}
