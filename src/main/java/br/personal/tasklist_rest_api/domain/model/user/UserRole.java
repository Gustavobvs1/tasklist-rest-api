package br.personal.tasklist_rest_api.domain.model.user;

import lombok.Getter;

@Getter
public enum UserRole {

    ADMIN("admin"),
    MODERATOR("moderator"),
    USER("user");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

}
