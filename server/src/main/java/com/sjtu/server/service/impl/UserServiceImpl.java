package com.sjtu.server.service.impl;

import com.sjtu.server.domain.User;
import com.sjtu.server.repository.UserRepository;
import com.sjtu.server.security.SecurityUtil;
import com.sjtu.server.security.UserDetailService;
import com.sjtu.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
        final UserDetails userDetails = userDetailService.loadUserByUsername(username);
        Map<String,Object> map = new HashMap<>();
        map.put("username",userDetails.getUsername());
        final String token = SecurityUtil.generateToken(map);
        return token;
    }

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public Page<User> listUser(Specification<User> spec, Pageable pageable) {
        return userRepository.findAll(spec, pageable);
    }
}
