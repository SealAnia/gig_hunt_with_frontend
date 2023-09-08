package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "goods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long goodsId;

    @ManyToOne
    @JoinColumn(name = "master_id")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ToString.Include
    private Master master;

    @Column(name = "price", columnDefinition = "double", nullable = false)
    @ToString.Include
    private Double price;

    @Column(name = "description", columnDefinition = "varchar(225)", nullable = false)
    @ToString.Include
    private String description;

    @ManyToMany(mappedBy = "basket", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Customer> customers;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(goodsId);
        result = prime * result + Double.hashCode(price);
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

    public boolean equals(Goods goods) {
        if(goods == this) {
            return true;
        }
        if(goods == null || goods.getClass() != this.getClass()) {
            return false;
        }
        Goods goodsTwo = (Goods) goods;
        return goodsId == goodsTwo.goodsId
                && (price == goodsTwo.price || (price != null && price == goodsTwo.price))
                && (description == goodsTwo.description || (description != null && description.equals(goodsTwo.description)));
    }

}