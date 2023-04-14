package br.com.lucas.Container.Store.repository;

import br.com.lucas.Container.Store.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Cliente_repository extends JpaRepository<Cliente , Long> {
    Optional<Cliente> findByEmail(String email);


}
