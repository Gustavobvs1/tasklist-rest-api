package br.personal.tasklist_rest_api.controllers;

import br.personal.tasklist_rest_api.domain.model.user.AuthResponseDTO;
import br.personal.tasklist_rest_api.domain.model.user.LoginDTO;
import br.personal.tasklist_rest_api.domain.model.user.RegisterDTO;
import br.personal.tasklist_rest_api.domain.model.user.User;
import br.personal.tasklist_rest_api.domain.repositories.UserRepository;
import br.personal.tasklist_rest_api.infra.security.TokenService;
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
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterDTO body) {
        if(this.userRepository.findByEmail(body.email()) != null) return ResponseEntity.status(HttpStatus.CONFLICT).build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(body.password());
        User newUser = new User(body.name(), body.email(), encryptedPassword, body.role());

        this.userRepository.save(newUser);

        var token = this.tokenService.generateToken(newUser);

        return ResponseEntity.ok().body(new AuthResponseDTO(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO body) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(body.email(),body.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = this.tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok().body(new AuthResponseDTO(token));
    }
}
