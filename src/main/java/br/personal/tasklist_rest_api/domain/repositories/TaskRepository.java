package br.personal.tasklist_rest_api.domain.repositories;

import br.personal.tasklist_rest_api.domain.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task,String> {
}
