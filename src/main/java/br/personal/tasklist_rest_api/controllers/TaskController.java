package br.personal.tasklist_rest_api.controllers;

import br.personal.tasklist_rest_api.domain.model.task.Task;
import br.personal.tasklist_rest_api.domain.model.task.TaskCreateDTO;
import br.personal.tasklist_rest_api.domain.model.task.TaskUpdateDTO;
import br.personal.tasklist_rest_api.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TaskCreateDTO body, @AuthenticationPrincipal UserDetails userDetails) {
        this.taskService.create(body, userDetails.getUsername());
        return ResponseEntity.status(201).build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<List<Task>> findByUserId(@RequestParam("id") String userId) {
        return ResponseEntity.ok().body(this.taskService.findByUserId(userId));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@RequestBody TaskUpdateDTO body, @RequestParam("id") String id) {
        this.taskService.update(body,id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@RequestParam String id) {
        this.taskService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok().body(this.taskService.findAll());
    }
}
