package com.example.todo_backend.domain.service;

import com.example.todo_backend.domain.model.Tasks;
import com.example.todo_backend.domain.repository.TasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {
  private final TasksRepository tasksRepository;

    public List<Tasks> findAllTasks() {
        return tasksRepository.findAll();
    }
}
