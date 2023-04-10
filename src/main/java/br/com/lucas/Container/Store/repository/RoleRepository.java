package br.com.lucas.Container.Store.repository;

import br.com.lucas.Container.Store.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
