package br.personal.tasklist_rest_api.controllers.exceptions;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleConflict(IllegalArgumentException conflictException) {
        return new ResponseEntity<String>("Este email já está cadastrado", HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleLoginError(AuthenticationException badRequestException) {
        return new ResponseEntity<String>("Email ou senha invalidos!", HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<String> handleMissingData(PSQLException psqlException) {
        return new ResponseEntity<String>("Estão faltando alguns dados", HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleWrongId(NoSuchElementException noSuchElementException) {
        return new ResponseEntity<String>("Id não encontrado", HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleNotFoundEndpoints(Exception notFoundEndpoint) {
        return new ResponseEntity<String>("Endpoint inválido", HttpStatus.NOT_FOUND);
    }
}
