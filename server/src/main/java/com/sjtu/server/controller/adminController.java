package com.sjtu.server.controller;

import com.sjtu.server.vo.JSONResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class adminController {

    @Value("${jwt.admin.username}")
    private String admin;

    @GetMapping("/mine")
    public String Mine() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(username.equals(admin)) {
            return JSONResult.fillResponseString(0, "", username);
        }else{
            return JSONResult.fillResponseString(400, "非管理员", "");
        }
    }

}
