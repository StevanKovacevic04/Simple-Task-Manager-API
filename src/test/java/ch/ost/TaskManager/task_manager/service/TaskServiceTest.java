package ch.ost.TaskManager.task_manager.service;

import ch.ost.TaskManager.task_manager.mapper.TaskMapper;
import ch.ost.TaskManager.task_manager.model.Task;
import ch.ost.TaskManager.task_manager.model.User;
import ch.ost.TaskManager.task_manager.model.dto.TaskRequest;
import ch.ost.TaskManager.task_manager.model.dto.TaskResponse;
import ch.ost.TaskManager.task_manager.repository.TaskRepository;
import ch.ost.TaskManager.task_manager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private TaskMapper taskMapper;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        userRepository = mock(UserRepository.class);
        taskMapper = mock(TaskMapper.class);
        taskService = new TaskService(taskRepository, userRepository, taskMapper);
    }

    @Test
    void testCreateTask() {
        TaskRequest request = new TaskRequest(
                "Test Task",
                "Test Description",
                LocalDate.of(2025, 4, 20),
                "HIGH",
                "PENDING",
                1L
        );

        User user = User.builder()
                .username("testuser")
                .email("test@example.com")
                .build();
        user.setId(1L);

        Task task = Task.builder()
                .title(request.title())
                .description(request.description())
                .dueDate(request.dueDate())
                .priority(Task.Priority.valueOf(request.priority()))
                .status(Task.Status.valueOf(request.status()))
                .user(user)
                .build();

        TaskResponse response = new TaskResponse(
                1L,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority().name(),
                task.getStatus().name(),
                user.getId()
        );

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(taskMapper.toEntity(request)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(task);
        when(taskMapper.toResponse(task)).thenReturn(response);

        TaskResponse result = taskService.createTask(request);

        assertEquals("Test Task", result.title());
        assertEquals("HIGH", result.priority());
        assertEquals(1L, result.userId());

        verify(taskRepository).save(task);
    }
}

