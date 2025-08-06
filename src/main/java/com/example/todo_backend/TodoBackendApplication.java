package com.example.todo_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.todo_backend.infrastructure.mapper")
public class TodoBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoBackendApplication.class, args);
	}

}
