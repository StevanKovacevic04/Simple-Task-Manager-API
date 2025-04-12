package ch.ost.TaskManager.task_manager.service;

import ch.ost.TaskManager.task_manager.mapper.UserMapper;
import ch.ost.TaskManager.task_manager.model.User;
import ch.ost.TaskManager.task_manager.model.dto.UserRequest;
import ch.ost.TaskManager.task_manager.model.dto.UserResponse;
import ch.ost.TaskManager.task_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }

    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserResponse createUser(UserRequest request) {
        User user = userMapper.toEntity(request);
        return userMapper.toResponse(userRepository.save(user));
    }
}

