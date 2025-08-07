package com.example.todo_backend.infrastructure.repository.impl;

import com.example.todo_backend.domain.model.TasksModel;
import com.example.todo_backend.domain.repository.TasksRepository;
import com.example.todo_backend.infrastructure.mapper.TasksMapper;
import com.example.todo_backend.infrastructure.entity.Tasks;
import com.example.todo_backend.domain.model.TaskStatus;
import java.time.ZoneId;
import java.util.Date;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TasksRepositoryImpl implements TasksRepository {

    private final TasksMapper tasksMapper;

    @Override
    public List<TasksModel> findAll() {
        return tasksMapper.select(c -> c).stream()
                .map(this::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public TasksModel findById(Integer taskId) {
        Tasks entity = tasksMapper.selectByPrimaryKey(taskId).orElse(null);
        return entity != null ? toDomainModel(entity) : null;
    }

    @Override
    public int insert(TasksModel domainTask) {
        Tasks entity = toEntity(domainTask);
        return tasksMapper.insertSelective(entity);
    }

    @Override
    public int update(TasksModel domainTask) {
        Tasks entity = toEntity(domainTask);
        return tasksMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int deleteById(Integer taskId) {
        return tasksMapper.deleteByPrimaryKey(taskId);
    }

    private TasksModel toDomainModel(Tasks entity) {
        TasksModel domain = new TasksModel();
        domain.setId(entity.getId());
        domain.setTitle(entity.getTitle());
        domain.setDescription(entity.getDescription());
        domain.setStatus(TaskStatus.valueOf(entity.getStatus()));
        domain.setDueDate(entity.getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return domain;
    }

    private Tasks toEntity(TasksModel domain) {
        Tasks entity = new Tasks();
        entity.setId(domain.getId());
        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        entity.setStatus(domain.getStatus().name());
        entity.setDueDate(Date.from(domain.getDueDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return entity;
    }
}