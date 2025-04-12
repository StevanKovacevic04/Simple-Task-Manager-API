package ch.ost.TaskManager.task_manager.model.dto;

import java.time.LocalDate;

public record TaskResponse(
        Long id,
        String title,
        String description,
        LocalDate dueDate,
        String priority,
        String status
) {}

