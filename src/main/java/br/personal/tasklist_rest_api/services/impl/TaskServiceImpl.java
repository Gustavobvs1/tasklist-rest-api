package br.personal.tasklist_rest_api.services.impl;

import br.personal.tasklist_rest_api.domain.model.task.*;
import br.personal.tasklist_rest_api.domain.model.user.User;
import br.personal.tasklist_rest_api.domain.repositories.TaskRepository;
import br.personal.tasklist_rest_api.domain.repositories.UserRepository;
import br.personal.tasklist_rest_api.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public void create(TaskCreateDTO body, String userEmail) {
        User user = (User) this.userRepository.findByEmail(userEmail);

        Task newTask = new Task(body);

        newTask.setCreatedAt(LocalDateTime.now());
        newTask.setUpdatedAt(LocalDateTime.now());
        newTask.setTaskStatus(TaskStatus.PENDING);
        newTask.setUser(user);

        this.taskRepository.save(newTask);
    }

    @Override
    public void update(TaskUpdateDTO body, String id) {
        Task newTask = this.taskRepository.findById(id).orElseThrow(NoSuchElementException::new);

        newTask.setTitle(body.title());
        newTask.setDescription(body.description());
        newTask.setTaskStatus(body.taskStatus());
        newTask.setTaskPriority(body.taskPriority());

        newTask.setUpdatedAt(LocalDateTime.now());

        this.taskRepository.save(newTask);
    }

    @Override
    public void delete(String id) {
        this.taskRepository.deleteById(id);
    }

    @Override
    public List<TaskResponseDTO> findByUserEmail(String userEmail) {
        User user = (User) this.userRepository.findByEmail(userEmail);
        return user.getTasks().stream().map(TaskResponseDTO::new).toList();
    }

    @Override
    public List<TaskResponseDTO> findAllTasksDTO() {
        return this.taskRepository.findAllTasksDTO();
    }
}
