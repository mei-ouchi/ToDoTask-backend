package com.example.todo_backend.application.controller.impl;

import com.example.todo_backend.domain.service.TasksService;


import com.example.todo_backend.application.controller.api.TasksApi;
import com.example.todo_backend.application.dto.TaskRequest;
import com.example.todo_backend.application.dto.TasksDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


import java.time.ZoneId;
import java.util.Date;

import java.time.ZonedDateTime;



@RestController
@RequiredArgsConstructor
public class TasksController implements TasksApi {

    private final TasksService tasksService;


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
        } else {
            dto.setDueDate(null);
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
            ZonedDateTime zonedDateTime = taskRequest.getDueDate().atStartOfDay(ZoneId.systemDefault());
            domainTask.setDueDate(Date.from(zonedDateTime.toInstant()));
        } else {
            domainTask.setDueDate(null);
        }
        return domainTask;
    }
}