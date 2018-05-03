package com.sjtu.server.controller;

import com.sjtu.server.domain.User;
import com.sjtu.server.service.impl.UserServiceImpl;
import com.sjtu.server.vo.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class loginController {

    @Autowired
    private UserServiceImpl userService;

    @Value("${jwt.admin.username}")
    private String adminUsername;

    @Value("${jwt.admin.password}")
    private String adminPassword;

    @PostMapping("${jwt.auth-path}")
    public String login(@RequestBody User obj) {
        if(obj.getUsername().equals(adminUsername) && obj.getPassword().equals(adminPassword)) {
            final String token = userService.login(obj.getUsername(), obj.getPassword());
            return JSONResult.fillResponseString(0, "success", token);
        }else{
            User user = userService.findByUserName(obj.getUsername());

            if(user == null) {
                return JSONResult.fillResponseString(500, "用户名或者密码错误！", "");
            }else if(!user.getPassword().equals(obj.getPassword())) {
                return JSONResult.fillResponseString(500, "用户名或者密码错误！", "");
            }

            final String token = userService.login(obj.getUsername(), obj.getPassword());
            return JSONResult.fillResponseString(0, "success", token);
        }

    }

    @PostMapping("/api/register")
    public String register(@RequestBody User user) {
        try {
            User rep = userService.register(user);
            return JSONResult.fillResponseString(0, "success", rep.toString());
        }catch (Exception ex) {
            return JSONResult.fillResponseString(500, ex.getMessage(), "");
        }
    }

    @GetMapping("/api/check/{username}")
    public String Check(@PathVariable("username") String username) {
        if(username == null) {
            return JSONResult.fillResponseString(500, "不能为空", "");
        }else{
            User user = userService.findByUserName(username);
            if(user != null) {
                return JSONResult.fillResponseString(500, "邮箱已注册", "");
            }
            return JSONResult.fillResponseString(0, "ok", "");
        }
    }

}
