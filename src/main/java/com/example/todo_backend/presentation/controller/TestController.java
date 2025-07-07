package com.example.todo_backend.presentation.controller;

import com.example.todo_backend.application.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test-db")
    public String testDbConnection() {
        Integer result = testService.getOne();
        return "DB接続テスト結果: " + result;
    }
}