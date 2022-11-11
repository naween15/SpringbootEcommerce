package com.example.completeecommerce.Services;

import com.example.completeecommerce.Configuration.JwtUtil;
import com.example.completeecommerce.Entity.JwtRequest;
import com.example.completeecommerce.Entity.JwtResponse;
import com.example.completeecommerce.Entity.User;
import com.example.completeecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
       String userName= jwtRequest.getUserName();
       String userPassword=jwtRequest.getUserPassword();
       authenticate(userName,userPassword);
       final UserDetails userDetails=loadUserByUsername(userName);
       String newGeneratedToken= jwtUtil.generateToken(userDetails);
       User user=userRepository.findById(userName).get();
       return new JwtResponse(newGeneratedToken,user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user= userRepository.findById(username).get();
       if (user!=null){
           return new org.springframework.security.core.userdetails.User(
                   user.getUserName(),user.getUserPassword(),
                   getAuthority(user));
       }else {
           throw new UsernameNotFoundException("username is not valid");
       }

    }

    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }
    private void authenticate(String userName,String userPassword) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,userPassword));

        }catch (DisabledException e){
            throw new Exception("User is disabled");
        }catch (BadCredentialsException e){
            throw new Exception("Bad credentials from user");
        }
    }
}
