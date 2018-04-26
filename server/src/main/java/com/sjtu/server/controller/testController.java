package com.sjtu.server.controller;

import com.sjtu.server.vo.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class testController {
    @GetMapping("/test")
    public ResponseEntity<String> Test() {
        return new ResponseEntity<String>("hello world", HttpStatus.OK);
    }
}
