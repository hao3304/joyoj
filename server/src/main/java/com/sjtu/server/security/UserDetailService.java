package com.sjtu.server.security;

import com.sjtu.server.domain.User;
import com.sjtu.server.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals("admin")) {
            List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            SecurityUser securityUser = new SecurityUser(username, "",  true, true, true,true, simpleGrantedAuthorities);
            return securityUser;
        }else {
            User user = userService.findByUserName(username);
            if(user == null) {
                throw  new UsernameNotFoundException("no user");
            }
            SecurityUser securityUser = new SecurityUser(user.getUsername(), user.getPassword(),  true, true, true,true, getAuthorities(user));
            return securityUser;
        }

    }

    public Collection<? extends GrantedAuthority> getAuthorities(User user) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        return simpleGrantedAuthorities;
    }
}
