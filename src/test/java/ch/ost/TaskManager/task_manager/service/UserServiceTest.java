package ch.ost.TaskManager.task_manager.service;

import ch.ost.TaskManager.task_manager.mapper.UserMapper;
import ch.ost.TaskManager.task_manager.model.User;
import ch.ost.TaskManager.task_manager.model.dto.UserRequest;
import ch.ost.TaskManager.task_manager.model.dto.UserResponse;
import ch.ost.TaskManager.task_manager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userMapper = mock(UserMapper.class);
        userService = new UserService(userRepository, userMapper);
    }

    @Test
    void testCreateUser() {
        UserRequest request = new UserRequest("john", "john@example.com");

        User user = User.builder()
                .username("john")
                .email("john@example.com")
                .build();

        UserResponse response = new UserResponse(1L, "john", "john@example.com");

        when(userMapper.toEntity(request)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toResponse(user)).thenReturn(response);

        UserResponse result = userService.createUser(request);

        assertEquals("john", result.username());
        assertEquals("john@example.com", result.email());

        verify(userRepository).save(user);
    }

    @Test
    void testGetUserById_NotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.getUserById(99L);
        });

        assertEquals("User not found", exception.getMessage());
    }
}

