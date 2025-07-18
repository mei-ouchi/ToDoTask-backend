package com.example.todo_backend.application.controller.impl;

import com.example.todo_backend.domain.service.TasksService;

import jakarta.validation.Valid;

import com.example.todo_backend.application.controller.api.TasksApi;
import com.example.todo_backend.application.dto.TaskRequest;
import com.example.todo_backend.application.dto.TasksDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class TasksController implements TasksApi {

    private final TasksService tasksService;

    // タスク一覧取得
    @Override
    public ResponseEntity<List<TasksDto>> getAllTasks() {
        List<com.example.todo_backend.domain.model.Tasks> domainTasks = tasksService.findAllTasks();

        List<TasksDto> responseTasks = domainTasks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseTasks);
    }

    // 特定のタスク取得
    @Override
    public ResponseEntity<TasksDto> getTaskById(Integer id) {
        com.example.todo_backend.domain.model.Tasks domainTask = tasksService.findById(id);
        TasksDto responseDto = convertToDto(domainTask);
        return ResponseEntity.ok(responseDto);
    }

    // タスクの作成
    @Override
    public ResponseEntity<TasksDto> createTask(
            @Valid @org.springframework.web.bind.annotation.RequestBody TaskRequest taskRequest) {
        com.example.todo_backend.domain.model.Tasks newTask = convertToDomain(taskRequest);
        com.example.todo_backend.domain.model.Tasks createdTask = tasksService.createTask(newTask);

        TasksDto responseDto = convertToDto(createdTask);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // タスクの更新
    @Override
    public ResponseEntity<TasksDto> updateTask(@PathVariable("id") Integer id,
            @Valid @RequestBody TaskRequest taskRequest) {
        com.example.todo_backend.domain.model.Tasks updatedTask = convertToDomain(taskRequest);
        updatedTask.setId(id);

        com.example.todo_backend.domain.model.Tasks resultTask = tasksService.updateTask(updatedTask);
        TasksDto responseDto = convertToDto(resultTask);

        return ResponseEntity.ok(responseDto);
    }

    // タスクの削除
    @Override
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Integer id) {
        tasksService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private TasksDto convertToDto(com.example.todo_backend.domain.model.Tasks domainTask) {
        TasksDto dto = new TasksDto();
        dto.setId(domainTask.getId());
        dto.setTitle(domainTask.getTitle());
        dto.setDescription(domainTask.getDescription());

        if (domainTask.getStatus() != null) {
            try {
                dto.setStatus(TasksDto.StatusEnum.fromValue(domainTask.getStatus()));
            } catch (IllegalArgumentException e) {
                System.err.println("Unknown status value: " + domainTask.getStatus());
                dto.setStatus(TasksDto.StatusEnum.PENDING);
            }
        } else {
            dto.setStatus(TasksDto.StatusEnum.PENDING);
        }

        if (domainTask.getDueDate() != null) {
            dto.setDueDate(domainTask.getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        return dto;
    }

    private com.example.todo_backend.domain.model.Tasks convertToDomain(TaskRequest taskRequest) {
        com.example.todo_backend.domain.model.Tasks domainTask = new com.example.todo_backend.domain.model.Tasks();
        domainTask.setTitle(taskRequest.getTitle());
        domainTask.setDescription(taskRequest.getDescription());

        if (taskRequest.getStatus() != null) {
            domainTask.setStatus(taskRequest.getStatus().getValue());
        } else {
            domainTask.setStatus(TasksDto.StatusEnum.PENDING.getValue());
        }

        if (taskRequest.getDueDate() != null) {
            domainTask.setDueDate(Date.from(taskRequest.getDueDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        return domainTask;
    }
}