package com.example.todo_backend.domain.service;

import com.example.todo_backend.infrastructure.mapper.TestMapper;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private final TestMapper testMapper;

    public TestService(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    public Integer getOne() {
        return testMapper.selectOne();
    }
}
