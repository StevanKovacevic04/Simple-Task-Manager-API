package ch.ost.TaskManager.task_manager.mapper;

import ch.ost.TaskManager.task_manager.model.User;
import ch.ost.TaskManager.task_manager.model.dto.UserRequest;
import ch.ost.TaskManager.task_manager.model.dto.UserResponse;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User user);

    @Mapping(target = "tasks", ignore = true)
        // we don't map tasks from UserRequest
    User toEntity(UserRequest request);
}


