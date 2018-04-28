package com.sjtu.server.service;

import com.sjtu.server.domain.User;

public interface UserService {
    User findByUserName(String name);
    String login(String username, String password);
}
