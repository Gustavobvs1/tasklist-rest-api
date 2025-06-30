package br.personal.tasklist_rest_api.services;

import br.personal.tasklist_rest_api.domain.model.user.LoginDTO;
import br.personal.tasklist_rest_api.domain.model.user.RegisterDTO;
import br.personal.tasklist_rest_api.infra.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;

public interface AuthService {

    String register(RegisterDTO body, TokenService tokenService);

    String login(LoginDTO body, AuthenticationManager authenticationManager, TokenService tokenService);
}
