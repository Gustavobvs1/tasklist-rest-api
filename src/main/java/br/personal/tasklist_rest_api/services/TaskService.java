package br.personal.tasklist_rest_api.services;


import br.personal.tasklist_rest_api.domain.model.task.Task;
import br.personal.tasklist_rest_api.domain.model.task.TaskCreateDTO;
import br.personal.tasklist_rest_api.domain.model.task.TaskUpdateDTO;

import java.util.List;

public interface TaskService {

    List<Task> findByUserId(String userId);

    void create(TaskCreateDTO taskCreateDTO, String userEmail);

    void update(TaskUpdateDTO taskUpdateDTO, String id);

    void delete(String id);

    List<Task> findAll();
}
