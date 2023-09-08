package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(orderId);
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + quantity;
        result = prime * result + ((cost == null) ? 0 : Double.hashCode(cost));
        result = prime * result + ((status == null) ? 0 : status.hashCode());
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
        return orderId == orderTwo.orderId
                && (date == orderTwo.date || (date != null && date.equals(orderTwo.date)))
                && quantity == orderTwo.quantity
                && (cost == orderTwo.cost || (cost != null && cost.equals(orderTwo.cost)))
                && (status == orderTwo.status || (status != null && status.equals(orderTwo.status)));
    }

    @PrePersist
    public void logNewOrderDetailsAttempt() {
        log.info("Attempting to add new order with id: " + orderId);
    }

    @PostPersist
    public void logNewOrderDetailsAdd() {
        log.info("Added order with ID: " + orderId);
    }

    @PostLoad
    public void countCost() {
        Master master = goods.getMaster();
        log.info(master.getNickname());
        cost = quantity * goods.getPrice();
        log.info("COST = " + cost);
    }

}
