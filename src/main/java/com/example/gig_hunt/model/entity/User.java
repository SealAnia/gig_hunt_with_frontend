package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("U")
@ToString(includeFieldNames = true)
public abstract class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    protected Long userId;

    @Column(name = "nickname", columnDefinition = "varchar(45)", nullable = false, unique = true)
    @ToString.Include
    protected String nickname;

    //NEW
    @Column(name = "password", columnDefinition = "varchar(225)", nullable = false, unique = false)
    @ToString.Exclude
    @JsonIgnore
    protected String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ToString.Include
    protected Role role;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name", columnDefinition = "TEXT", nullable = false)),
            @AttributeOverride(name = "secondName", column = @Column(name = "second_name", columnDefinition = "TEXT", nullable = false)),
            @AttributeOverride(name = "phoneNumber", column = @Column(name = "phone_number", columnDefinition = "BIGINT", nullable = false, unique = true)),
            @AttributeOverride(name = "watsappNumber", column = @Column(name = "watsapp_number", columnDefinition = "BIGINT", unique = true)),
            @AttributeOverride(name = "email", column = @Column(name = "e_mail", columnDefinition = "TEXT", nullable = false, unique = true)),
            @AttributeOverride(name = "address", column = @Column(name = "address", columnDefinition = "TEXT", unique = true)),
            @AttributeOverride(name = "postalCode", column = @Column(name = "postal_code", columnDefinition = "NUMBER")),
    })
    @ToString.Exclude
    protected PersonalData personalData;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    protected List<OrderDetails> orders;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private Card card;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "basket",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "goods_id"))
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @JsonIgnore
    private List<Goods> basket;

    //NEW
    @Column(name = "account_not_expired", columnDefinition = "bit(1)", nullable = false, unique = false)
    @JsonIgnore
    private boolean accountNonExpired;
    @Column(name = "account_not_locked", columnDefinition = "bit(1)", nullable = false, unique = false)
    @JsonIgnore
    private boolean accountNonLocked;
    @Column(name = "credentials_not_expired", columnDefinition = "bit(1)", nullable = false, unique = false)
    @JsonIgnore
    private boolean credentialsNonExpired;
    @Column(name = "enabled", columnDefinition = "bit(1)", nullable = false, unique = false)
    @JsonIgnore
    private boolean enabled;

    public void addGoodsToBasket(Goods goods) {
        this.basket.add(goods);
        goods.getCustomers().add((Customer) this);
    }

    //NEW
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorities = new ArrayList<GrantedAuthority>();
        if (role != null) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}