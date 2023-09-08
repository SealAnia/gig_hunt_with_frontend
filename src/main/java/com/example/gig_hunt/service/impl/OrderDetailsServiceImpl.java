package com.example.gig_hunt.service.impl;

import com.example.gig_hunt.model.entity.Master;
import com.example.gig_hunt.model.entity.OrderDetails;
import com.example.gig_hunt.model.entity.OrderStatus;
import com.example.gig_hunt.model.repository.OrderDetailsRepository;
import com.example.gig_hunt.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final PaymentServiceImpl paymentService;

    @Autowired
    public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository, PaymentServiceImpl paymentService) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.paymentService = paymentService;
    }

    @Override
    public List<OrderDetails> getAll() {
        return orderDetailsRepository.findAll();
    }

    @Override
    public OrderDetails readById(Long id) {
        return orderDetailsRepository.findById(id).get();
    }

    @Override
    public OrderDetails createOrUpdate(OrderDetails orderDetails) {
        orderDetails.setStatus(OrderStatus.WAITING_FOR_REPLY);
        if(orderDetails.getQuantity() == 0) {
            orderDetails.setQuantity(1);
        }
        return orderDetailsRepository.saveAndFlush(orderDetails);
    }

    @Override
    public void delete(Long id) {
        orderDetailsRepository.deleteById(id);
    }

    @Override
    public List<OrderDetails> getOrdersOfMaster(Long userId) {
        return orderDetailsRepository.getOrdersOfMaster(userId);
    }

    //MASTER CHOOSES TO ACCEPT OR DECLINE AN ORDER
    @Override
    public OrderDetails acceptOrder(OrderDetails orderDetails) {
        if(orderDetails.getStatus().equals(OrderStatus.WAITING_FOR_REPLY)) {
            orderDetails.setDate(orderDetails.getDate());
            orderDetails.setCustomer(orderDetails.getCustomer());
            orderDetails.setGoods(orderDetails.getGoods());
            orderDetails.setQuantity(orderDetails.getQuantity());
            orderDetails.setStatus(OrderStatus.IN_PROGRESS);

            //CHANGE FINDING MASTER BY AUTHENTICATION

            Master master = orderDetails.getGoods().getMaster();
            int activeOrders = master.getActiveOrders();
            int maximumOrders = master.getMaximum();

            activeOrders = activeOrders + 1;
            if(activeOrders == maximumOrders) {
                master.setBusy(true);
            }
            master.setActiveOrders(activeOrders);
        }
        return orderDetailsRepository.saveAndFlush(orderDetails);
    }

    @Override
    public OrderDetails declineOrder(OrderDetails orderDetails) {
        if(orderDetails.getStatus().equals(OrderStatus.WAITING_FOR_REPLY)) {
            orderDetails.setDate(orderDetails.getDate());
            orderDetails.setCustomer(orderDetails.getCustomer());
            orderDetails.setGoods(orderDetails.getGoods());
            orderDetails.setQuantity(orderDetails.getQuantity());
            orderDetails.setStatus(OrderStatus.DECLINED);
        }
        return orderDetailsRepository.saveAndFlush(orderDetails);
    }

    //CUSTOMER SETS STATUS TO COMPLETED WHEN RECIEVES THE ORDER
    @Override
    public OrderDetails setOrderCompleted(OrderDetails orderDetails) {
        if(orderDetails.getStatus().equals(OrderStatus.IN_PROGRESS)) {
            orderDetails.setDate(orderDetails.getDate());
            orderDetails.setCustomer(orderDetails.getCustomer());
            orderDetails.setGoods(orderDetails.getGoods());
            orderDetails.setQuantity(orderDetails.getQuantity());
            orderDetails.setStatus(OrderStatus.COMPLETED);
            Master master = orderDetails.getGoods().getMaster();
            int activeOrders = orderDetails.getGoods().getMaster().getActiveOrders();
            master.setActiveOrders(activeOrders - 1);
            if(master.isBusy()) {
                master.setBusy(false);
            }
        }

        Long cardOfCustomerId = orderDetails.getCustomer().getCard().getCardId();
        Long cardOfMasterId = orderDetails.getGoods().getMaster().getCard().getCardId();
        Long orderId = orderDetails.getOrderId();
        paymentService.pay(cardOfCustomerId, cardOfMasterId, orderId);

        return orderDetailsRepository.saveAndFlush(orderDetails);
    }

}