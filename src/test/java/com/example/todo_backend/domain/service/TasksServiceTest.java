package com.example.todo_backend.domain.service;

import com.example.todo_backend.domain.exception.TaskNotFoundException;
import com.example.todo_backend.domain.model.TaskStatus;
import com.example.todo_backend.domain.model.TasksModel;
import com.example.todo_backend.domain.repository.TasksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TasksServiceTest {

    @Mock
    private TasksRepository tasksRepository;

    @InjectMocks
    private TasksService tasksService;

    private TasksModel sampleTask;

    @BeforeEach
    void setUp() {
        sampleTask = TasksModel.builder()
                .id(1)
                .title("JUnitテスト")
                .description("タスクのテスト")
                .status(TaskStatus.PENDING)
                .dueDate(LocalDate.of(2025, 12, 31))
                .build();
    }

    @Test
    void testFindAllTasks_returnsTasksList() {
        // Mocking: tasksRepository.findAll() が呼び出されたら、sampleTask を含むリストを返す
        when(tasksRepository.findAll()).thenReturn(List.of(sampleTask));

        // Testing: findAllTasks() を実行
        List<TasksModel> result = tasksService.findAllTasks();

        // Assertion: 結果が正しいか検証
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("JUnitテスト", result.get(0).getTitle());
    }

    @Test
    void testFindById_existingTask_returnsTask() {
        // Mocking: tasksRepository.findById(1) が呼び出されたら、sampleTask を返す
        when(tasksRepository.findById(1)).thenReturn(sampleTask);

        // Testing
        TasksModel result = tasksService.findById(1);

        // Assertion
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("JUnitテスト", result.getTitle());
    }

    @Test
    void testFindById_nonExistingTask_throwsTaskNotFoundException() {
        // Mocking: tasksRepository.findById(999) が呼び出されたら、null を返す
        when(tasksRepository.findById(999)).thenReturn(null);

        // Assertion: TaskNotFoundException がスローされることを検証
        assertThrows(TaskNotFoundException.class, () -> tasksService.findById(999));
    }

    @Test
    void testCreateTask_successful_returnsCreatedTask() {
        // Mocking
        TasksModel newTask = TasksModel.builder()
                .title("新しいタスク")
                .description("新規作成テスト")
                .status(TaskStatus.PENDING)
                .dueDate(LocalDate.now())
                .build();
        when(tasksRepository.insert(any(TasksModel.class))).thenReturn(1);

        // Testing
        TasksModel createdTask = tasksService.createTask(newTask);

        // Assertion
        assertNotNull(createdTask);
        assertEquals("新しいタスク", createdTask.getTitle());
        verify(tasksRepository, times(1)).insert(any(TasksModel.class));
    }

    @Test
    void testCreateTask_failure_throwsRuntimeException() {
        // Mocking
        TasksModel newTask = TasksModel.builder().title("失敗タスク").build();
        when(tasksRepository.insert(any(TasksModel.class))).thenReturn(0);

        // Assertion: RuntimeException がスローされることを検証
        assertThrows(RuntimeException.class, () -> tasksService.createTask(newTask));
    }

    @Test
    void testUpdateTask_successful_returnsUpdatedTask() {
        // Mocking
        TasksModel updatedTask = TasksModel.builder()
                .id(1)
                .title("更新済みタイトル")
                .status(TaskStatus.COMPLETED)
                .build();
        when(tasksRepository.update(any(TasksModel.class))).thenReturn(1);

        // Testing
        TasksModel result = tasksService.updateTask(updatedTask);

        // Assertion
        assertNotNull(result);
        assertEquals("更新済みタイトル", result.getTitle());
        verify(tasksRepository, times(1)).update(any(TasksModel.class));
    }

    @Test
    void testDeleteById_successful_deletesTask() {
        // Mocking
        when(tasksRepository.deleteById(1)).thenReturn(1);

        // Testing: 例外がスローされないことを検証
        assertDoesNotThrow(() -> tasksService.deleteById(1));

        // Assertion: tasksRepository.deleteById(1) が1回呼び出されたことを検証
        verify(tasksRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteById_nonExistingTask_throwsTaskNotFoundException() {
        // Mocking
        when(tasksRepository.deleteById(999)).thenReturn(0);

        // Assertion: TaskNotFoundException がスローされることを検証
        assertThrows(TaskNotFoundException.class, () -> tasksService.deleteById(999));
    }

    @Test
    void testCompleteTask_updatesStatusToCompleted() {
        // Testing
        TasksModel completedTask = tasksService.completeTask(sampleTask);

        // Assertion
        assertEquals(TaskStatus.COMPLETED, completedTask.getStatus());
    }

    @Test
    void testIsOverdue_taskIsOverdue_returnsTrue() {
        // Mock task with past due date
        TasksModel overdueTask = TasksModel.builder()
                .id(1)
                .dueDate(LocalDate.of(2024, 1, 1))
                .build();

        // Testing
        boolean isOverdue = tasksService.isOverdue(overdueTask, LocalDate.now());

        // Assertion
        assertTrue(isOverdue);
    }

    @Test
    void testIsOverdue_taskIsNotOverdue_returnsFalse() {
        // Mock task with future due date
        TasksModel notOverdueTask = TasksModel.builder()
                .id(1)
                .dueDate(LocalDate.of(2026, 1, 1))
                .build();

        // Testing
        boolean isOverdue = tasksService.isOverdue(notOverdueTask, LocalDate.now());

        // Assertion
        assertFalse(isOverdue);
    }
}