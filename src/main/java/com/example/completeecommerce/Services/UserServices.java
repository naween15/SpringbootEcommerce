package com.example.completeecommerce.Services;

import com.example.completeecommerce.Entity.Role;
import com.example.completeecommerce.Entity.User;
import com.example.completeecommerce.Repository.RoleRepository;
import com.example.completeecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public User createUser(User user){
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        return userRepository.save(user);
    }

//    public void initRoleAndUser(){
//        Role adminRole=new Role();
//        adminRole.setRoleName("ADMIN");
//        adminRole.setRoleDescription("A person with complete control over the system");
//        roleRepository.save(adminRole);
//
//        Role userRole=new Role();
//        userRole.setRoleName("USER");
//        userRole.setRoleDescription("A person who uses the facilities of system");
//        roleRepository.save(userRole);
//
//        User  adminUser=new User();
//        adminUser.setUserName("nawin");
//        adminUser.setUserFirstName("nabin");
//        adminUser.setUserLastName("parajuli");
//        adminUser.setUserPassword(getEncodedPassword("dadadon"));
//        Set<Role> roles=new HashSet<>();
//        roles.add(adminRole);
//        adminUser.setRole(roles);
//        userRepository.save( adminUser);
//
//        User  user=new User();
//        user.setUserName("sawin");
//        user.setUserFirstName("sabin");
//        user.setUserLastName("parajuli");
//        user.setUserPassword(getEncodedPassword("bhairaja"));
//        Set<Role> roles1=new HashSet<>();
//        roles1.add(userRole);
//        user.setRole(roles1);
//        userRepository.save(user);
//
//
//
//    }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }
}
