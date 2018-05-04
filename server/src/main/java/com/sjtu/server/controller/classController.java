package com.sjtu.server.controller;

import com.sjtu.server.domain.Class;
import com.sjtu.server.service.impl.ClassServiceImpl;
import com.sjtu.server.vo.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class classController {

    @Autowired
    private ClassServiceImpl classService;

    @GetMapping("/class")
    public String getList() {
        return JSONResult.fillResponseString(0,"",0);
    }

    @PostMapping("/class")
    public String add(@RequestBody Class obj) {
       Class result =  classService.add(obj);
       return JSONResult.fillResponseString(0,"success", result.toString());
    }

}
