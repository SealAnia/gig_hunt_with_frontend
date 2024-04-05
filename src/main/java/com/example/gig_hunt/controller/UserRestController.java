package com.example.gig_hunt.controller;

import com.example.gig_hunt.model.entity.*;
import com.example.gig_hunt.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/users")
public class UserRestController {

    private final UserServiceImpl userService;

    @Autowired
    public UserRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping(value = "/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.readById(userId);
    }

    @GetMapping(value = "/{nickname}/")
    public User findByNickname(@PathVariable String nickname) {
        return userService.findByNickname(nickname);
    }

    @GetMapping(value = "/{nickname}/{password}")
    public User findByNicknameAndPassword(@PathVariable String nickname, @PathVariable String password) {
        return userService.findByNicknameAndPassword(nickname, password);
    }

    //RETURNS ORDERS THAT A CERTAIN CUSTOMER HAS ORDERED
    @GetMapping(value = "/{userId}/orders")
    public Set<OrderDetails> findOrdersForUser(@PathVariable Long userId) {
        return userService.findOrdersForUser(userId);
    }

    //RETURNS ALL MASTERS IN A CERTAIN ONLINE CATEGORY
    @GetMapping(value = "/masters/{categoryId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public Set<Master> findMastersInCategory(@PathVariable Long categoryId) {
        return userService.findMastersInCategory(categoryId);
    }

    //RETURNS ALL MASTER FROM A CERTAIN TOWN IN AN OFFLINE CATEGORY
    @GetMapping(value = "/masters/{categoryId}/{townId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public Set<Master> findMastersInCategoryAndFromTown(@PathVariable Long categoryId, @PathVariable Long townId) {
        return userService.findMastersInCategoryAndFromTown(categoryId, townId);
    }

    //RETURNS TOTAL AMOUNT THAT A CERTAIN MASTER HAS EARNED
    @GetMapping(value = "/masters/amount/{userId}")
    public Double countAmountForMaster(@PathVariable Long userId) {
        System.out.println(userService.countEarnedAmount(userId));
        return userService.countEarnedAmount(userId);
    }

    //RETURNS THE AMOUNT THAT A MASTER HAS EARNED IN A CERTAIN MONTH
    @GetMapping(value = "/masters/amount_for_month/{userId}")
    public String countAmontEarnedInMonth(@PathVariable Long userId,
                                          @RequestParam(value = "month") String month) {
        return userService.countEarnedAmountForMonth(userId, month);
    }

    @PostMapping(value = "/add_admin")
    public void addAdmin(@RequestBody Admin admin) {
        userService.createOrUpdate(admin);
    }

    @PostMapping(value = "/register_as_customer")
    public void createUser(@RequestBody Customer customer) {
        userService.createOrUpdate(customer);
    }

    @PostMapping(value = "/register_as_master")
    public void createMaster(@RequestBody Master master) {
        userService.createOrUpdate(master);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<Customer> updateUser(@PathVariable Long userId, @RequestBody Customer customer) {
        Customer updatedCustomer = (Customer)userService.readById(userId);
        updatedCustomer.setNickname(customer.getNickname());
        updatedCustomer.setPersonalData(customer.getPersonalData());
        userService.createOrUpdate(updatedCustomer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PutMapping(value = "/master/{userId}")
    public ResponseEntity<Master> updateMaster(@PathVariable Long userId, @RequestBody Master master) {
        Master updatedMaster = (Master) userService.readById(userId);
        updatedMaster.setNickname(master.getNickname());
        updatedMaster.setTown(master.getTown());
        updatedMaster.setCategory(master.getCategory());
        updatedMaster.setMaximum(master.getMaximum());
        updatedMaster.setPersonalData(master.getPersonalData());
        userService.createOrUpdate(updatedMaster);
        return ResponseEntity.ok(updatedMaster);
    }

    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
    }

}
