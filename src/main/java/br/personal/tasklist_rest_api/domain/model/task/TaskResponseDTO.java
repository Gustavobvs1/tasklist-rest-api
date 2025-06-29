package br.personal.tasklist_rest_api.domain.model.task;

import java.time.LocalDateTime;

public record TaskResponseDTO(String id, String title, String description, TaskStatus taskStatus, TaskPriority taskPriority, LocalDateTime createdAt, LocalDateTime updatedAt, String userEmail) {
    public TaskResponseDTO(Task task) {
        this(task.getId(),task.getTitle(),task.getDescription(),task.getTaskStatus(),task.getTaskPriority(),task.getCreatedAt(),task.getUpdatedAt(),task.getUser().getEmail());
    }
}
