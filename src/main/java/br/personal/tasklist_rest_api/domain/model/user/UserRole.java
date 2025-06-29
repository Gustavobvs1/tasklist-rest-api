package br.personal.tasklist_rest_api.domain.model.user;

import lombok.Getter;

@Getter
public enum UserRole {

    ADM("adm"),
    MODERATOR("moderator"),
    U("u");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

}
