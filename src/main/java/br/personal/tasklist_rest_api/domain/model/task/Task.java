package br.personal.tasklist_rest_api.domain.model.task;

import br.personal.tasklist_rest_api.domain.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tasks")
@Table(name = "tasks")
@EqualsAndHashCode(of = "id")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private TaskPriority taskPriority;

    private TaskStatus taskStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Task(TaskCreateDTO taskCreateDTO) {
        this.title = taskCreateDTO.title();
        this.description = taskCreateDTO.description();
        this.taskPriority = taskCreateDTO.taskPriority();
    }
}
