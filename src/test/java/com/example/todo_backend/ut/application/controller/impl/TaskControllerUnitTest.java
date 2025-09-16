package com.example.todo_backend.ut.application.controller.impl;

import com.example.todo_backend.application.controller.impl.TasksController;
import com.example.todo_backend.domain.model.TaskStatus;
import com.example.todo_backend.domain.model.TasksModel;
import com.example.todo_backend.domain.service.TasksService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TasksController.class)
public class TaskControllerUnitTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TasksService tasksService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testGetAllTasks_returnsTasksList() throws Exception {
    TasksModel sampleTask = new TasksModel(1, "テストタスク", "説明", TaskStatus.PENDING, LocalDate.now());
    when(tasksService.findAllTasks()).thenReturn(List.of(sampleTask));

    mockMvc.perform(get("/tasks"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].title").value("テストタスク"));
  }
}
