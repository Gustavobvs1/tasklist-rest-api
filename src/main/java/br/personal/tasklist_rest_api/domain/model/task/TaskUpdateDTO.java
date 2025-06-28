package br.personal.tasklist_rest_api.domain.model.task;

public record TaskUpdateDTO(String title, String description, TaskStatus taskStatus, TaskPriority taskPriority) {
}
