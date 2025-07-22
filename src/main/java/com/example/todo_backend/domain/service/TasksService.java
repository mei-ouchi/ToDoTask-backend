package com.example.todo_backend.domain.service;

import com.example.todo_backend.domain.model.TasksModel;
import com.example.todo_backend.domain.repository.TasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.todo_backend.domain.exception.TaskNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {
  private final TasksRepository tasksRepository;

  // タスク一覧の取得
  public List<TasksModel> findAllTasks() {
    return tasksRepository.findAll();
  }

  // 特定のタスク取得
  public TasksModel findById(Integer id) {
    TasksModel task = tasksRepository.findById(id);
    if (task == null) {
      throw new TaskNotFoundException("Task not found with ID: " + id);
    }
    return task;
  }

  // タスクの作成
  public TasksModel createTask(TasksModel task) {
    int insertedRows = tasksRepository.insert(task);
    if (insertedRows == 0) {
      throw new RuntimeException("Failed to create task: No rows inserted.");
    }
    return task;
  }

  // タスクの更新
  public TasksModel updateTask(TasksModel task) {
    if (task.getId() == null) {
      throw new IllegalArgumentException("Task ID cannot be null for update operation.");
    }
    int updatedRows = tasksRepository.update(task);
    if (updatedRows == 0) {
      throw new TaskNotFoundException("Task not found with ID: " + task.getId());
    }
    return task;
  }

  // タスクの削除
  public void deleteById(Integer id) {
    int deletedRows = tasksRepository.deleteById(id);
    if (deletedRows == 0) {
      throw new TaskNotFoundException("Task not found with ID: " + id);
    }
  }
}