package ch.ost.TaskManager.task_manager.model;

import ch.ost.TaskManager.task_manager.enumeration.Priority;
import ch.ost.TaskManager.task_manager.enumeration.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task extends DefaultEntity{

    private String title, description;

    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
