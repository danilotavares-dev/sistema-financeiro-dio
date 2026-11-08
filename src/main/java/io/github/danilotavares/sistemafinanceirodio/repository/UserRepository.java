package io.github.danilotavares.sistemafinanceirodio.repository;

import io.github.danilotavares.sistemafinanceirodio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByCpf(String cpf);
    public boolean existsByEmail(String email);
}
