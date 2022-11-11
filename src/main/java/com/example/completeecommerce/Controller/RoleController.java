package com.example.completeecommerce.Controller;

import com.example.completeecommerce.Entity.Role;
import com.example.completeecommerce.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/create-role")
    public Role createRole(@RequestBody Role role){
       Role createdRole= roleService.createRole(role);
       return createdRole;
    }
}
