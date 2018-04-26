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

/**
 * Created by jack on 2017/12/13.
 */
@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserServiceImpl userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);
        if(user==null){
            throw new UsernameNotFoundException("no user");
        }
        SecurityUser securityUser = new SecurityUser(user.getUsername(),user.getPassword(),true,true,true,true,getAuthorities(user));
        return securityUser;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(User user) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        return simpleGrantedAuthorities;
    }


}
