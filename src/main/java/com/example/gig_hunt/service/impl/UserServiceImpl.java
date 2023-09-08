package com.example.gig_hunt.service.impl;

import com.example.gig_hunt.model.entity.*;
import com.example.gig_hunt.model.repository.OrderDetailsRepository;
import com.example.gig_hunt.model.repository.RoleRepository;
import com.example.gig_hunt.model.repository.UserRepository;
import com.example.gig_hunt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           OrderDetailsRepository orderDetailsRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User readById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User createOrUpdate(User user) {
        var role = new Role();
        if(user.getClass().equals(Customer.class)) {
            role = roleRepository.findById(Customer.getCUSTOMER_ROLE_ID()).get();
        }
        else if(user.getClass().equals(Master.class)) {
            role = roleRepository.findById(Master.getMASTER_ROLE_ID()).get();
        }
        else if(user.getClass().equals(Admin.class)) {
            var personalData = new PersonalData();
            personalData.setFirstName(Admin.getADMIN_FIRST_NAME());
            personalData.setSecondName(Admin.getADMIN_SECOND_NAME());
            personalData.setPhoneNumber(user.getPersonalData().getPhoneNumber());
            personalData.setEmail(user.getPersonalData().getEmail());
            user.setPersonalData(personalData);
            role = roleRepository.findById(Admin.getADMIN_ROLE_ID()).get();
        }
        user.setRole(role);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<OrderDetails> findOrdersForUser(Long userId) {
        User user = userRepository.findById(userId).get();
        return user.getOrders();
    }

    @Override
    public List<Master> findMastersInCategory(Long categoryId) {
        return userRepository.findMastersInCategory(categoryId);
    }

    @Override
    public List<Master> findMastersInCategoryAndFromTown(Long categoryId, Long townId) {
        return userRepository.findMastersInCategoryAndFromTown(categoryId,townId);
    }

    @Override
    public Double countEarnedAmount(Long masterId) {
        return userRepository.countEarnedAmount(masterId);
    }

    @Override
    public String countEarnedAmountForMonth(Long masterId, String month) {
        Master master = (Master) userRepository.findById(masterId).get();
        Company company = master.getCompany();
        if(userRepository.countEarnedAmountForMonth(masterId, month) > Master.getAMOUNT_FREE_FROM_FEE() && company == null) {
            return String.valueOf(userRepository.countEarnedAmountForMonth(masterId, month)) +
                    " You have earned more than " + Master.getAMOUNT_FREE_FROM_FEE()
                    + ". Remember to the register the company.";
        }
        return String.valueOf(userRepository.countEarnedAmountForMonth(masterId, month));
    }

}
