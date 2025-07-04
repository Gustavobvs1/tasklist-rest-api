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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username);
    }

    @Override
    public String register(RegisterDTO body, TokenService tokenService) {
        if(this.userRepository.findByEmail(body.email()) != null) throw new IllegalArgumentException();

        String encryptedPassword = new BCryptPasswordEncoder().encode(body.password());
        User newUser = new User(body.name(), body.email(), encryptedPassword, body.role());

        this.userRepository.save(newUser);

        return tokenService.generateToken(newUser);
    }

    @Override
    public String login(LoginDTO body, AuthenticationManager authenticationManager, TokenService tokenService) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(body.email(),body.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        return tokenService.generateToken((User) auth.getPrincipal());
    }
}
