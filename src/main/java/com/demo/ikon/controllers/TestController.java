package com.demo.ikon.controllers;

import com.demo.ikon.dtos.GetTestRequest;
import com.demo.ikon.dtos.ResponseApi;
import com.demo.ikon.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestService testService;
    @PostMapping("/testApi")
    private ResponseApi test(@RequestBody GetTestRequest request){
        return testService.getTest(request);
    }
}
