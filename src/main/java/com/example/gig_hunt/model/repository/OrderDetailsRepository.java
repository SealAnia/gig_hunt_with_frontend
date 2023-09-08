package com.example.gig_hunt.model.repository;

import com.example.gig_hunt.model.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    @Query(value = "SELECT o.order_id, o.date, o.customer_id, o.goods_id, o.quantity, o.cost, " +
            "o.order_status FROM order_details o " +
            "INNER JOIN goods g ON o.goods_id = g.goods_id " +
            "WHERE g.master_id = :user_id", nativeQuery = true)
    List<OrderDetails> getOrdersOfMaster(@Param("user_id") Long userId);

}
