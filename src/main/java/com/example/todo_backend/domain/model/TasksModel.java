package com.example.todo_backend.domain.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TasksModel {
    private Integer id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate dueDate;
}