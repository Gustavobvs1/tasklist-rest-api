package br.personal.tasklist_rest_api.domain.model.task;

import lombok.Getter;

@Getter
public enum TaskStatus {

    COMPLETED("completed"),
    ON_PROGRESS("on_progress"),
    PENDING("pending");

    private String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public void setTaskStatus(String status) {
        this.status = status;
    }
}
