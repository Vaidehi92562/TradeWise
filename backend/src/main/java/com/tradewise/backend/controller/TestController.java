package com.tradewise.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test")
    public String testApi() {
        return "TradeWise backend is running successfully!";
    }

    @GetMapping("/api/hello")
    public String helloApi() {
        return "Hello from TradeWise backend!";
    }
}