package com.sjtu.server.service;

import com.sjtu.server.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface UserService {
    User findByUserName(String name);
    String login(String username, String password);
    User register(User user);
    Page<User> listUser(Specification<User> spec, Pageable pageable);
}
