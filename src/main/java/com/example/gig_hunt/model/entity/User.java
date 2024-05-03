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
import java.util.Set;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("U")
@ToString(includeFieldNames = true)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long userId;

    @Column(name = "nickname", columnDefinition = "varchar(45)", nullable = false, unique = true)
    @ToString.Include
    private String nickname;

    @Column(name = "password", columnDefinition = "varchar(225)", nullable = false, unique = false)
    @ToString.Exclude
    @JsonIgnore
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ToString.Include
    private Role role;

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
    private PersonalData personalData;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private Set<OrderDetails> orders;

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
    @ToString.Exclude
    private List<Goods> basket;

    @Column(name = "account_not_expired", columnDefinition = "bit(1)", nullable = false, unique = false)
    @JsonIgnore
    @ToString.Exclude

    private boolean accountNonExpired;
    @Column(name = "account_not_locked", columnDefinition = "bit(1)", nullable = false, unique = false)
    @JsonIgnore
    @ToString.Exclude
    private boolean accountNonLocked;

    @Column(name = "credentials_not_expired", columnDefinition = "bit(1)", nullable = false, unique = false)
    @JsonIgnore
    @ToString.Exclude
    private boolean credentialsNonExpired;

    @Column(name = "enabled", columnDefinition = "bit(1)", nullable = false, unique = false)
    @JsonIgnore
    @ToString.Exclude
    private boolean enabled;

    public void addGoodsToBasket(Goods goods) {
        this.getBasket().add(goods);
        goods.getCustomers().add((Customer) this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorities = new ArrayList<GrantedAuthority>();
        if (getRole() != null) {
            authorities.add(new SimpleGrantedAuthority(getRole().getName()));
            System.out.println(getRole().getName());
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return getNickname();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(getUserId());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getRole() == null) ? 0: getRole().hashCode());
        result = prime * result + ((getPersonalData() == null) ? 0 : getPersonalData().hashCode());
        result = prime * result + Boolean.hashCode(isAccountNonExpired());
        result = prime * result + Boolean.hashCode(isAccountNonLocked());
        result = prime * result + Boolean.hashCode(isCredentialsNonExpired());
        result = prime * result + Boolean.hashCode(isEnabled());
        return result;
    }

    public boolean equals(User user) {
        if(user == this) {
            return true;
        }
        if(user == null || user.getClass() != this.getClass()) {
            return false;
        }
        User userTwo = (User) this;
        return getUserId() == userTwo.getUserId() &&
                (getNickname() == userTwo.getNickname() || (getNickname() != null && getNickname().equals(userTwo.getNickname()))) &&
                (getPassword() == userTwo.getPassword() || (getPassword() != null && getPassword().equals(userTwo.getPassword()))) &&
                (getRole() == userTwo.getRole() || (getRole() != null && getRole().equals(userTwo.getRole()))) &&
                (getPersonalData() == null || (getPersonalData() != null && getPersonalData().equals(userTwo.getPersonalData()))) &&
                (getCard() == null || (getCard() != null && getCard().equals(userTwo.getCard()))) &&
                isAccountNonExpired() == userTwo.isAccountNonExpired() &&
                isAccountNonLocked() == userTwo.isAccountNonLocked() &&
                isCredentialsNonExpired() == userTwo.isCredentialsNonExpired() &&
                isEnabled() == userTwo.isEnabled();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public Set<OrderDetails> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderDetails> orders) {
        this.orders = orders;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<Goods> getBasket() {
        return basket;
    }

    public void setBasket(List<Goods> basket) {
        this.basket = basket;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}