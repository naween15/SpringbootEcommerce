package com.example.completeecommerce.Services;

import com.example.completeecommerce.Entity.Role;
import com.example.completeecommerce.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public Role createRole(Role role){
       Role createdRole = roleRepository.save(role);
       return  role;
    }
}
