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

    public enum TaskStatus {
        PENDING,
        COMPLETED;
    }

    private TaskStatus status;

    private LocalDate dueDate;

    public void complete() {
        this.status = TaskStatus.COMPLETED;
    }

    public boolean isOverdue(LocalDate today) {
        if (dueDate == null) {
            return false;
        }
        return dueDate.isBefore(today);
    }

}