package br.personal.tasklist_rest_api.controllers;

import br.personal.tasklist_rest_api.domain.model.user.AuthResponseDTO;
import br.personal.tasklist_rest_api.domain.model.user.LoginDTO;
import br.personal.tasklist_rest_api.domain.model.user.RegisterDTO;
import br.personal.tasklist_rest_api.domain.model.user.User;
import br.personal.tasklist_rest_api.domain.repositories.UserRepository;
import br.personal.tasklist_rest_api.infra.security.TokenService;
import br.personal.tasklist_rest_api.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterDTO body) {

        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponseDTO(this.authService.register(body)));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO body) {

        return ResponseEntity.ok().body(new AuthResponseDTO(this.authService.login(body)));
    }
}
