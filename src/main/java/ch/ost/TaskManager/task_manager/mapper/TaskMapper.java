package ch.ost.TaskManager.task_manager.mapper;

import ch.ost.TaskManager.task_manager.model.Task;
import ch.ost.TaskManager.task_manager.model.dto.TaskRequest;
import ch.ost.TaskManager.task_manager.model.dto.TaskResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "user.id", target = "userId")
    TaskResponse toResponse(Task task);

    @Mapping(target = "user", ignore = true)
        // user is injected manually in service
    Task toEntity(TaskRequest request);
}
