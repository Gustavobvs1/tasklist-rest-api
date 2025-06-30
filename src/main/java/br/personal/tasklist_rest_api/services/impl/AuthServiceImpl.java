package br.personal.tasklist_rest_api.services.impl;

import br.personal.tasklist_rest_api.domain.model.user.LoginDTO;
import br.personal.tasklist_rest_api.domain.model.user.RegisterDTO;
import br.personal.tasklist_rest_api.domain.model.user.User;
import br.personal.tasklist_rest_api.domain.repositories.UserRepository;
import br.personal.tasklist_rest_api.infra.security.TokenService;
import br.personal.tasklist_rest_api.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements UserDetailsService, AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username);
    }

    @Override
    public String register(RegisterDTO body) {
        if(this.userRepository.findByEmail(body.email()) != null) throw new RuntimeException("");

        String encryptedPassword = new BCryptPasswordEncoder().encode(body.password());
        User newUser = new User(body.name(), body.email(), encryptedPassword, body.role());

        this.userRepository.save(newUser);

        return this.tokenService.generateToken(newUser);
    }

    @Override
    public String login(LoginDTO body) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(body.email(),body.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return this.tokenService.generateToken((User) auth.getPrincipal());
    }
}
