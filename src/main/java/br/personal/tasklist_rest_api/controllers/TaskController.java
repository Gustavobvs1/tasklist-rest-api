package br.personal.tasklist_rest_api.controllers;

import br.personal.tasklist_rest_api.domain.model.task.Task;
import br.personal.tasklist_rest_api.domain.model.task.TaskCreateDTO;
import br.personal.tasklist_rest_api.domain.model.task.TaskResponseDTO;
import br.personal.tasklist_rest_api.domain.model.task.TaskUpdateDTO;
import br.personal.tasklist_rest_api.domain.repositories.UserRepository;
import br.personal.tasklist_rest_api.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @PostMapping
    public ResponseEntity<?> create(@RequestBody TaskCreateDTO body, @AuthenticationPrincipal UserDetails userDetails) {
        this.taskService.create(body, userDetails.getUsername());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(location).body(location);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> findByUserEmail(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok().body(this.taskService.findByUserEmail(userDetails.getUsername()));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@RequestBody TaskUpdateDTO body, @PathVariable("id") String id, @AuthenticationPrincipal UserDetails userDetails) {
        this.taskService.update(body,id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id, @AuthenticationPrincipal UserDetails userDetails) {
        this.taskService.delete(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<TaskResponseDTO>> findAllTasksDTO() {

        return ResponseEntity.ok().body(this.taskService.findAllTasksDTO());
    }
}
