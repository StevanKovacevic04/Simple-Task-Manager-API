package ch.ost.TaskManager.task_manager.mapper;

import ch.ost.TaskManager.task_manager.model.User;
import ch.ost.TaskManager.task_manager.model.dto.UserRequest;
import ch.ost.TaskManager.task_manager.model.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    User toEntity(UserRequest request);
}

