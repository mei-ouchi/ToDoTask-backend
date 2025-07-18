package com.example.todo_backend.infrastructure.repository.impl;

import com.example.todo_backend.domain.model.Tasks;
import com.example.todo_backend.domain.repository.TasksRepository;
import com.example.todo_backend.infrastructure.mapper.TasksMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import static com.example.todo_backend.infrastructure.mapper.TasksDynamicSqlSupport.id;

@Repository
@RequiredArgsConstructor
public class TasksRepositoryImpl implements TasksRepository {

    private final TasksMapper tasksMapper;

    @Override
    public List<Tasks> findAll() {
        return tasksMapper.select(c -> c);
    }

    @Override
    public Optional<Tasks> findById(Integer taskId) {
        return tasksMapper.selectByPrimaryKey(taskId);
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
        return tasksMapper.delete(c -> c.where(id, isEqualTo(taskId)));
    }
}