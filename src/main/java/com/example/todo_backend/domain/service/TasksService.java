package com.example.todo_backend.domain.service;

import com.example.todo_backend.domain.model.Tasks;
import com.example.todo_backend.domain.repository.TasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TasksService {
  private final TasksRepository tasksRepository;

  //タスク一覧の取得
  public List<Tasks> findAllTasks() {
        return tasksRepository.findAll();
    }

  //特定のタスク取得
  public Optional<Tasks> findById(Integer id) {
       return tasksRepository.findById(id);
  }

  //タスクの作成
  public Tasks createTask(Tasks task) {
        int insertedRows = tasksRepository.insert(task);
        if (insertedRows == 0) {
            throw new RuntimeException("Failed to create task.");
        }
        return task;
    }

  //タスクの更新
  public Optional<Tasks> updateTask(Tasks task) {
        Optional<Tasks> existingTask = tasksRepository.findById(task.getId());
        if (existingTask.isEmpty()) {
            return Optional.empty();
        }

        int updatedRows = tasksRepository.update(task);
        
        if (updatedRows == 0) {
            throw new RuntimeException("Failed to update task with ID: " + task.getId());
        }

        return tasksRepository.findById(task.getId());
    }

  //タスクの削除
  public boolean deleteById(Integer id) {
        Optional<Tasks> existingTask = tasksRepository.findById(id);
        if (existingTask.isEmpty()) {
            return false;
        }
        
        int deletedRows = tasksRepository.deleteById(id);
        return deletedRows > 0;
    }
}