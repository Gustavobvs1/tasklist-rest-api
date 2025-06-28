package br.personal.tasklist_rest_api.domain.repositories;

import br.personal.tasklist_rest_api.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,String> {
    UserDetails findByEmail(String email);

}
