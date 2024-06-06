// package com.example.toppou.service;
package dgi.dic2.a4l0u_c0d3.toppou20.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dgi.dic2.a4l0u_c0d3.toppou20.model.CustomUserDetails;
import dgi.dic2.a4l0u_c0d3.toppou20.model.User;
import dgi.dic2.a4l0u_c0d3.toppou20.repository.UserRepository;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements  UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.debug("Entering in loadUserByUsername Method...");
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            logger.error("Username not found: " + username);
            throw new UsernameNotFoundException("could not found user..!!");
        }else{
            User gotUser = user.get();
//            logger.info("User Authenticated Successfully..!!!");
            return new CustomUserDetails(gotUser);

        }
    }
}
