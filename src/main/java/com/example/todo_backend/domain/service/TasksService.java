package com.example.todo_backend.domain.service;

import com.example.todo_backend.domain.model.Tasks;
import com.example.todo_backend.domain.repository.TasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.todo_backend.domain.exception.TaskNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {
  private final TasksRepository tasksRepository;

  //タスク一覧の取得
  public List<Tasks> findAllTasks() {
        return tasksRepository.findAll();
    }

  //特定のタスク取得
  public Tasks findById(Integer id) {
        return tasksRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + id));
    }

  //タスクの作成
  public Tasks createTask(Tasks task) {
        int insertedRows = tasksRepository.insert(task);
        if (insertedRows == 0) {
            throw new RuntimeException("Failed to create task: No rows inserted.");
        }
        return task;
    }

  //タスクの更新
  public Tasks updateTask(Tasks task) {
        tasksRepository.findById(task.getId())
                .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + task.getId()));

        int updatedRows = tasksRepository.update(task);
        if (updatedRows == 0) {
            throw new RuntimeException("Failed to update task with ID: " + task.getId() + ". No rows affected.");
        }
        return tasksRepository.findById(task.getId())
                    .orElseThrow(() -> new TaskNotFoundException("Updated task not found after update, ID: " + task.getId()));
    }

  //タスクの削除
  public void deleteById(Integer id) {
        tasksRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + id));
        
        int deletedRows = tasksRepository.deleteById(id);
        if (deletedRows == 0) {
            throw new RuntimeException("Failed to delete task with ID: " + id + ". No rows affected.");
        }
    }
}