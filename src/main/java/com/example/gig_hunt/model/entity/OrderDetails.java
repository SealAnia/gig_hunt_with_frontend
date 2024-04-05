package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.annotation.Transient;

import java.util.Date;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class OrderDetails {

    private static Log log = LogFactory.getLog(OrderDetails.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long orderId;

    @Column(name = "date", columnDefinition = "date", nullable = false)
    @ToString.Include
    private Date date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ToString.Include
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ToString.Include
    private Goods goods;

    @Column(name = "quantity", columnDefinition = "number default = 1", nullable = false)
    @ToString.Include
    private int quantity;

    @Transient
    private Double cost;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus status;

    public static Log getLog() {
        return log;
    }

    public static void setLog(Log log) {
        OrderDetails.log = log;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(getOrderId());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getCustomer() == null) ? 0 : getCustomer().hashCode());
        result = prime * result + ((getGoods() == null) ? 0 : getGoods().hashCode());
        result = prime * result + getQuantity();
        result = prime * result + ((getCost() == null) ? 0 : Double.hashCode(getCost()));
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    public boolean equals(OrderDetails order) {
        if(order == this) {
            return true;
        }
        if(order == null || order.getClass() != this.getClass()) {
            return false;
        }
        OrderDetails orderTwo = (OrderDetails) order;
        return getOrderId() == orderTwo.getOrderId()
                && (getDate() == orderTwo.getDate() || (getDate() != null && getDate().equals(orderTwo.getDate())))
                && (getCustomer() == orderTwo.getCustomer() || getCustomer() != null && getCustomer().equals(orderTwo.getCustomer()))
                && (getGoods() == orderTwo.getGoods() || getGoods() != null && getGoods().equals(orderTwo.getGoods()))
                && getQuantity() == orderTwo.getQuantity()
                && (getCost() == orderTwo.getCost() || (getCost() != null && getCost().equals(orderTwo.getCost())))
                && (getStatus() == orderTwo.getStatus() || (getStatus() != null && getStatus().equals(orderTwo.getStatus())));
    }

    @PrePersist
    public void logNewOrderDetailsAttempt() {
        getLog().info("Attempting to add new order with id: " + getOrderId());
    }

    @PostPersist
    public void logNewOrderDetailsAdd() {
        getLog().info("Added order with ID: " + getOrderId());
    }

    @PostLoad
    public void countCost() {
        Master master = getGoods().getMaster();
        getLog().info(master.getNickname());
        setCost(getQuantity() * getGoods().getPrice());
        getLog().info("COST = " + getCost());
    }

}
