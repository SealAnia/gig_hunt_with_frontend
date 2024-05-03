package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "goods")
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
    @JsonIgnore
    @ToString.Exclude
    private List<Customer> customers;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(getGoodsId());
        result = prime * result + Double.hashCode(getPrice());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getMaster() == null) ? 0 : getMaster().hashCode());
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
        return getGoodsId() == goodsTwo.getGoodsId()
                && (getPrice() == goodsTwo.getPrice() || (getPrice() != null && getPrice() == goodsTwo.getPrice()))
                && (getDescription() == goodsTwo.getDescription() || (getDescription() != null && getDescription().equals(goodsTwo.getDescription())))
                && (getMaster() == goodsTwo.getMaster() || (getMaster() != null && getMaster().equals(goodsTwo.getMaster())));
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}