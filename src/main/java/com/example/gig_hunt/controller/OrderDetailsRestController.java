package com.example.gig_hunt.controller;

import com.example.gig_hunt.model.entity.OrderDetails;
import com.example.gig_hunt.service.impl.OrderDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/orders")
public class OrderDetailsRestController {

    private final OrderDetailsServiceImpl orderDetailsService;

    @Autowired
    public OrderDetailsRestController(OrderDetailsServiceImpl orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping(value = "")
    public List<OrderDetails> getAllOrders() {
        return orderDetailsService.getAll();
    }

    @GetMapping(value = "/{orderId}")
    public OrderDetails getOrderById(@PathVariable Long orderId) {
        return orderDetailsService.readById(orderId);
    }

    //RETURNS ALL ORDERS FROM A CERTAIN MASTER
    @GetMapping(value = "/my_orders/{userId}/")
    public List<OrderDetails> getOrdersOfMaster(@PathVariable Long userId) {
        return orderDetailsService.getOrdersOfMaster(userId);
    }

    //@GetMapping(value = "/{userId}/get_my_orders")
    @GetMapping(value = "/users/{userId}")
    public Set<OrderDetails> getOrdersOfCustomer(@PathVariable Long userId) {
        return orderDetailsService.getOrderForCustomer(userId);
    }

    @GetMapping(value = "/my_orders/{userId}/by_date")
    public List<OrderDetails> getOrdersOfMasterOrderByDate(@PathVariable Long userId) {
        return orderDetailsService.getOrdersOfMasterOrderByDate(userId);
    }

    @GetMapping(value = "/my_orders/{userId}/by_status")
    public List<OrderDetails> getOrdersOfMasterOrderByStatus(@PathVariable Long userId) {
        return orderDetailsService.getOrdersOfMasterOrderByStatus(userId);
    }

    @PostMapping(value = "/")
    public void addOrderDetails(@RequestBody OrderDetails order) {
        orderDetailsService.createOrUpdate(order);
    }

    @PutMapping(value = "/{orderId}")
    public ResponseEntity<OrderDetails> updateOrderDetails(@PathVariable Long orderId, @RequestBody OrderDetails order) {
        OrderDetails updatedOrder = orderDetailsService.readById(orderId);
        updatedOrder.setDate(order.getDate());
        updatedOrder.setGoods(order.getGoods());
        updatedOrder.setCustomer(order.getCustomer());
        updatedOrder.setQuantity(order.getQuantity());
        orderDetailsService.createOrUpdate(updatedOrder);
        return ResponseEntity.ok(updatedOrder);
    }

    @PutMapping(value = "/{orderId}/accept")
    public ResponseEntity<OrderDetails> acceptOrder(@PathVariable Long orderId, @RequestBody OrderDetails order) {
        OrderDetails orderToAccept = orderDetailsService.readById(orderId);
        orderDetailsService.acceptOrder(orderToAccept);
        return ResponseEntity.ok(orderToAccept);
    }

    @PutMapping(value = "/{orderId}/decline")
    public ResponseEntity<OrderDetails> declineOrder(@PathVariable Long orderId, @RequestBody OrderDetails order) {
        OrderDetails orderToDecline = orderDetailsService.readById(orderId);
        orderDetailsService.declineOrder(orderToDecline);
        return ResponseEntity.ok(orderToDecline);
    }

    @PutMapping(value = "/{orderId}/set_completed")
    public ResponseEntity<OrderDetails> setOrderCompleted(@PathVariable Long orderId, @RequestBody OrderDetails order) {
        OrderDetails orderToComplete = orderDetailsService.readById(orderId);
        orderDetailsService.setOrderCompleted(orderToComplete);
        return ResponseEntity.ok(orderToComplete);
    }

    @DeleteMapping(value = "/{orderId}")
    public void deleteOrderDetails(@PathVariable Long orderId) {
        orderDetailsService.delete(orderId);
    }

}
