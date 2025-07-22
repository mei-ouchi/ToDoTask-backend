package com.example.todo_backend.domain.repository;

import com.example.todo_backend.domain.model.TasksModel;
import java.util.List;

public interface TasksRepository {
    List<TasksModel> findAll();

    TasksModel findById(Integer id);

    int insert(TasksModel tasks);

    int update(TasksModel tasks);

    int deleteById(Integer id);
}
