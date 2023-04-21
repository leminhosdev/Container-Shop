package br.com.lucas.Container.Store.repository;

import br.com.lucas.Container.Store.entity.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Scrap_repository extends JpaRepository<Scrap, Long> {
    Optional<Scrap> findByLink(String link);
    boolean existsByLink(String link);
}
