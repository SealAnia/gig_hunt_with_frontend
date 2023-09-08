package com.example.gig_hunt.controller;

import com.example.gig_hunt.model.entity.Role;
import com.example.gig_hunt.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/roles")
public class RoleRestController {

    private final RoleServiceImpl roleService;

    @Autowired
    public RoleRestController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    public List<Role> getAllRoles() {
        return roleService.getAll();
    }

    @GetMapping(value = "/{roleId}")
    public Role getRoleById(@PathVariable Long roleId) {
        return roleService.readById(roleId);
    }

    @PostMapping(value = "/")
    public void createRole(@RequestBody Role role) {
        roleService.createOrUpdate(role);
    }

    @PutMapping(value = "/{roleId}")
    public ResponseEntity<Role> updateRole(@PathVariable Long roleId, @RequestBody Role role) {
        Role updatedRole = roleService.readById(roleId);
        updatedRole.setName(role.getName());
        roleService.createOrUpdate(updatedRole);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping(value = "/{roleId}")
    public void deleteRole(@PathVariable Long roleId) {
        roleService.delete(roleId);
    }

}
