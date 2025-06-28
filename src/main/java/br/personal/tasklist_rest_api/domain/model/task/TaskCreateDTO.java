package br.personal.tasklist_rest_api.domain.model.task;

public record TaskCreateDTO(String title, String description, TaskPriority taskPriority) {
}
