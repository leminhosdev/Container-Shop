package br.com.lucas.Container.Store.repository;

import br.com.lucas.Container.Store.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Cliente_repository extends JpaRepository<Cliente , Long> {

}
