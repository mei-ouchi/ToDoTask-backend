package com.example.todo_backend.domain.repository;

import com.example.todo_backend.domain.model.Tasks;
import java.util.List;
import java.util.Optional;

public interface TasksRepository {
    List<Tasks> findAll();
    Optional<Tasks> findById(Integer id);
    int insert(Tasks tasks);
    int update(Tasks tasks);
    int deleteById(Integer id);
}
