package ch.ost.TaskManager.task_manager.model.dto;

import java.time.LocalDate;

public record TaskRequest(
        String title,
        String description,
        LocalDate dueDate,
        String priority,
        String status,
        Long userId
) {}

