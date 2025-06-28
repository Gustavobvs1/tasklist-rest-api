package br.personal.tasklist_rest_api.domain.model.task;

import lombok.Getter;

@Getter
public enum TaskPriority {

    HIGH("high"),
    MEDIUM("medium"),
    LOW("low");

    private String priority;

    TaskPriority(String priority) {
        this.priority = priority;
    }

    public void setTaskPriority(String priority) {
        this.priority = priority;
    }
}
