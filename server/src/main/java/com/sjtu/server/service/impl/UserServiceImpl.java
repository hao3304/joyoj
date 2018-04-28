package com.sjtu.server.service.impl;

import com.sjtu.server.domain.User;
import com.sjtu.server.repository.UserRepository;
import com.sjtu.server.security.SecurityUtil;
import com.sjtu.server.security.UserDetailService;
import com.sjtu.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDetailService userDetailService;


    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username,password);
        final UserDetails userDetails = userDetailService.loadUserByUsername(username);
        Map<String,Object> map = new HashMap<>();
        map.put("username",userDetails.getUsername());
        final String token = SecurityUtil.generateToken(map);
        return token;
    }
}
