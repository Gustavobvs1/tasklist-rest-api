package br.personal.tasklist_rest_api.domain.repositories;

import br.personal.tasklist_rest_api.domain.model.task.Task;
import br.personal.tasklist_rest_api.domain.model.task.TaskResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,String> {
    @Query("SELECT new br.personal.tasklist_rest_api.domain.model.task.TaskResponseDTO(t.id,t.title, t.description,t.taskStatus,t.taskPriority,t.createdAt,t.updatedAt, u.email) FROM tasks t JOIN t.user u")
    List<TaskResponseDTO> findAllTasksDTO();
}
