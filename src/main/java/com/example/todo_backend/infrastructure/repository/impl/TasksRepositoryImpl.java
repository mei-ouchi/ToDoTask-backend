package com.example.todo_backend.infrastructure.repository.impl;

import com.example.todo_backend.domain.model.Tasks;
import com.example.todo_backend.domain.repository.TasksRepository;
import com.example.todo_backend.infrastructure.mapper.TasksMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TasksRepositoryImpl implements TasksRepository {

    private final TasksMapper tasksMapper;

    @Override
    public List<Tasks> findAll() {
        return tasksMapper.select(c -> c);
    }

    @Override
    public Tasks findById(Integer taskId) {
        return tasksMapper.selectByPrimaryKey(taskId).orElse(null);
    }

    @Override
    public int insert(Tasks newTask) {
        return tasksMapper.insertSelective(newTask);
    }

    @Override
    public int update(Tasks updatedTask) {
        if (updatedTask.getId() == null) {
            throw new IllegalArgumentException("Task ID cannot be null for update operation.");
        }
        return tasksMapper.updateByPrimaryKeySelective(updatedTask);
    }

    @Override
    public int deleteById(Integer taskId) {
        return tasksMapper.deleteByPrimaryKey(taskId);
    }
}