package ch.ost.TaskManager.task_manager.model.dto;

public record UserResponse(
        Long id,
        String username,
        String email
) {}

