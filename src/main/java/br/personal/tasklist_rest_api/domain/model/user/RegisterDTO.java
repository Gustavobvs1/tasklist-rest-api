package br.personal.tasklist_rest_api.domain.model.user;

public record RegisterDTO(String name, String email, String password, UserRole role) {
}
