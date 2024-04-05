package com.example.gig_hunt.service.impl;

import com.example.gig_hunt.model.entity.OrderStatus;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendHtmlEmailWithOrderProposition(String recepient, Long orderId) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        message.setRecipients(MimeMessage.RecipientType.TO, recepient);
        message.setSubject("Test email from Spring");

        //System.out.println("SENDING E-MAIL WITH ORDER_ID: " + orderId);

        String htmlContent = "<h1>You have a new order</h1>" +
                "<p><a href = http://localhost:8080/orders/" + orderId + ">View details</a></p>";

                //"<p>Accept it <a href=\"http://localhost:8080/orders/" + orderId + "/accept\">ACCEPT</a> or decline " +
                //"<a href=\"http://localhost:8080/orders/" + orderId + "/decline\">DECLINE</a></p>";
        message.setContent(htmlContent, "text/html; charset=utf-8");

        mailSender.send(message);
    }

    public void sendEmailWithOrderAcceptionToCustomer(String recepient, Long orderId, OrderStatus status,
                                                              double cost) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        message.setRecipients(MimeMessage.RecipientType.TO, recepient);
        message.setSubject("Order status has changed");

        String htmlContent = "<p>The order with id " + orderId + " is " + status.toString().toLowerCase() + "</p>" +
                "<p>The total cost is " + cost + "</p>" +
                "<p><a href = http://localhost:8080/orders/" + orderId + ">View details</a></p>";

        message.setContent(htmlContent, "text/html; charset=utf-8");

        mailSender.send(message);
    }

    public void sendEmailWithOrderDeclinationToCustomer(String recepient, Long orderId,
                                                        OrderStatus status) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        message.setRecipients(MimeMessage.RecipientType.TO, recepient);
        message.setSubject("Order status has changed");

        String htmlContent = "<p>The order with id " + orderId + " is " + status.toString().toLowerCase() + "</p>" +
                "<p><a href = http://localhost:8080/orders/" + orderId + ">View details</a></p>";

        message.setContent(htmlContent, "text/html; charset=utf-8");

        mailSender.send(message);
    }

}
