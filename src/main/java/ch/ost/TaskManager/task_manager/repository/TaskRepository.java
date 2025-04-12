package ch.ost.TaskManager.task_manager.repository;

import ch.ost.TaskManager.task_manager.enumeration.Status;
import ch.ost.TaskManager.task_manager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);
    List<Task> findByStatus(Status status);
}