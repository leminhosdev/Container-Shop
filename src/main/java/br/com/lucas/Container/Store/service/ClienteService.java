package br.com.lucas.Container.Store.service;

import br.com.lucas.Container.Store.entity.Cliente;
import br.com.lucas.Container.Store.http.controler.dto.filter.ClientFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente saving(Cliente cliente);
    Page<Cliente> listingClientes(ClientFilter clientFilter, Pageable pageable);
    Optional<Cliente> searchById(Long id);
    void deletedById(Long id);
}
