package com.example.completeecommerce.Controller;

import com.example.completeecommerce.Entity.Role;
import com.example.completeecommerce.Entity.User;
import com.example.completeecommerce.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserServices userServices;

//    @PostConstruct
//    public  void initRoleAndUser(){
//        userServices.initRoleAndUser();
//    }
    @GetMapping("/forAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String forAdmin(){
        return "forAdmin";

    }
    @GetMapping("/forUser")
    @PreAuthorize("hasRole('USER')")
    public String forUser(){
        return "forUser";
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        Role role=new Role();
        role.setRoleName("USER");
        role.setRoleDescription("A person who uses the system");
        Set<Role>  roles=new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        return userServices.createUser(user);
    }
}
