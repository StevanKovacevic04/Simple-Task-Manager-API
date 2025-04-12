package ch.ost.TaskManager.task_manager.mapper;

import ch.ost.TaskManager.task_manager.model.Task;
import ch.ost.TaskManager.task_manager.model.dto.TaskRequest;
import ch.ost.TaskManager.task_manager.model.dto.TaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "user.id", target = "userId")
    TaskResponse toResponse(Task task);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true) // set manually in service
    Task toEntity(TaskRequest request);
}
