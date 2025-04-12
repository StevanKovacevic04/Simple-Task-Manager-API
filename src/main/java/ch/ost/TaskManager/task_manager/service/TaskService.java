package ch.ost.TaskManager.task_manager.service;

import ch.ost.TaskManager.task_manager.mapper.TaskMapper;
import ch.ost.TaskManager.task_manager.model.Task;
import ch.ost.TaskManager.task_manager.model.User;
import ch.ost.TaskManager.task_manager.model.dto.TaskRequest;
import ch.ost.TaskManager.task_manager.model.dto.TaskResponse;
import ch.ost.TaskManager.task_manager.repository.TaskRepository;
import ch.ost.TaskManager.task_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toResponse)
                .collect(toList());
    }

    public TaskResponse getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public TaskResponse createTask(TaskRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = Task.builder()
                .title(request.title())
                .description(request.description())
                .dueDate(request.dueDate())
                .priority(Task.Priority.valueOf(request.priority()))
                .status(Task.Status.valueOf(request.status()))
                .user(user)
                .build();

        return taskMapper.toResponse(taskRepository.save(task));
    }

    public TaskResponse updateTask(Long id, TaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());
        task.setPriority(Task.Priority.valueOf(request.priority()));
        task.setStatus(Task.Status.valueOf(request.status()));

        if (!task.getUser().getId().equals(request.userId())) {
            User user = userRepository.findById(request.userId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            task.setUser(user);
        }

        return taskMapper.toResponse(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
