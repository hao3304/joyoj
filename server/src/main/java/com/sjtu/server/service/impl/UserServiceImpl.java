package com.sjtu.server.service.impl;


import com.sjtu.server.domain.User;
import com.sjtu.server.repository.UserRepository;
import com.sjtu.server.security.SecurityUtil;
import com.sjtu.server.security.UserDetailService;
import com.sjtu.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticate;

    @Autowired
    private UserDetailService userDetailService;


    @Override
    public User saveOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User findByUserName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public Page<User> listUser(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username,password);
        final Authentication authentication = authenticate.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailService.loadUserByUsername(username);
        Map<String,Object> map = new HashMap<>();
        map.put("username",userDetails.getUsername());
        final String token = SecurityUtil.generateToken(map);
        return token;

    }

}
