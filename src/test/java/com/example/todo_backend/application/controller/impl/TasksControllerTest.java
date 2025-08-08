package com.example.todo_backend.application.controller.impl;

import com.example.todo_backend.TodoBackendApplication;
import com.example.todo_backend.application.dto.TaskRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = TodoBackendApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DbUnitConfiguration(databaseConnection = "dataSource")
public class TasksControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @DatabaseSetup("/dataset/tasks.xml")
  @ExpectedDatabase(value = "/dataset/tasks.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
  void testGetAllTasks_returnsAllTasks() throws Exception {
    mockMvc.perform(get("/tasks"))// タスクの取得
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(3))
        .andExpect(jsonPath("$[0].title").value("買い物"));
  }

  @Test
  @DatabaseSetup("/dataset/tasks.xml")
  @ExpectedDatabase(value = "/dataset/tasks-expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
  void testCreateTask_successful_returnsCreatedTask() throws Exception {
    TaskRequest newTask = new TaskRequest();
    newTask.setTitle("新しいタスク");
    newTask.setDescription("新規タスクの説明");
    newTask.setStatus(TaskRequest.StatusEnum.PENDING);
    newTask.setDueDate(LocalDate.of(2025, 8, 20));

    mockMvc.perform(post("/tasks")// タスクの作成
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(newTask)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.title").value("新しいタスク"));
  }

  @Test
  @DatabaseSetup("/dataset/tasks.xml")
  @ExpectedDatabase(value = "/dataset/tasks-updated.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
  void testUpdateTask_successful_returnsUpdatedTask() throws Exception {
    TaskRequest updatedTask = new TaskRequest();
    updatedTask.setTitle("更新後のタイトル");
    updatedTask.setDescription("更新後の説明");
    updatedTask.setStatus(TaskRequest.StatusEnum.COMPLETED);
    updatedTask.setDueDate(LocalDate.of(2025, 8, 15));

    mockMvc.perform(put("/tasks/1")// タスクの更新
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(updatedTask)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("更新後のタイトル"));
  }

  @Test
  @DatabaseSetup("/dataset/tasks.xml")
  @ExpectedDatabase(value = "/dataset/tasks-deleted.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
  void testDeleteTask_successful_returnsNoContent() throws Exception {
    mockMvc.perform(delete("/tasks/1"))// タスクの削除
        .andExpect(status().isNoContent());
  }

  @Test
  @DatabaseSetup("/dataset/tasks.xml")
  void testGetTaskById_nonExistingTask_returnsNotFound() throws Exception {
    mockMvc.perform(get("/tasks/999"))
        .andExpect(status().isNotFound());
  }
}
