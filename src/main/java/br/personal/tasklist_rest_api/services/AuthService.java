package br.personal.tasklist_rest_api.services;

import br.personal.tasklist_rest_api.domain.model.user.LoginDTO;
import br.personal.tasklist_rest_api.domain.model.user.RegisterDTO;

public interface AuthService {

    String register(RegisterDTO body);

    String login(LoginDTO body);
}
