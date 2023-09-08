package com.example.gig_hunt.service.impl;

import com.example.gig_hunt.model.entity.Role;
import com.example.gig_hunt.model.repository.RoleRepository;
import com.example.gig_hunt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role readById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role createOrUpdate(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

}
