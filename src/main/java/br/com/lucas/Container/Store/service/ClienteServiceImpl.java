package br.com.lucas.Container.Store.service;

import br.com.lucas.Container.Store.entity.Cliente;
import br.com.lucas.Container.Store.http.controler.dto.filter.ClientFilter;
import br.com.lucas.Container.Store.repository.Cliente_repository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService{

    private final Cliente_repository clienteRepository;

    public Cliente saving(Cliente cliente){
        return clienteRepository.save(cliente);
    }
    public Page<Cliente> listingClientes(ClientFilter clientFilter, Pageable pageable){
       Cliente cliente = Cliente.builder().
               id(clientFilter.getId()).
               name(clientFilter.getName()).
               email(clientFilter.getEmail()).
               password(clientFilter.getPassword()).
               cpf(clientFilter.getCpf()).build();

       ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(cliente, exampleMatcher);

        return clienteRepository.findAll(example, pageable);
    }

    public Optional<Cliente> searchById(Long id){
        return clienteRepository.findById(id);
    }
    public void deletedById(Long id){
        clienteRepository.deleteById(id);
    }



}
