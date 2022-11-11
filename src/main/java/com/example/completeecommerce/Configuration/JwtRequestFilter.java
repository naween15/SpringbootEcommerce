package com.example.completeecommerce.Configuration;

import com.example.completeecommerce.Services.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String header=request.getHeader("Authorization");
        String token=null;
        String username=null;
       if(header!=null  && header.startsWith("Bearer ")){
           token=header.substring(7);
           System.out.println(token);
           try {
               username=jwtUtil.getUsernameFromToken(token);

           }catch (IllegalArgumentException e){
               System.out.println("unable to get jwt token");
           }catch (ExpiredJwtException e){
               System.out.println("token expired");
           }
       }else {
           System.out.println("jwt token start with Bearer ");
       }
       if (username!= null && SecurityContextHolder.getContext().getAuthentication() ==null){
          UserDetails userDetails= jwtService.loadUserByUsername(username);
          if(jwtUtil.validateToken(token,userDetails)){
              UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                      =new UsernamePasswordAuthenticationToken(userDetails,
                      null,
                      userDetails.getAuthorities());
              usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
              SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
          }
       }
       filterChain.doFilter(request,response);
    }
}
