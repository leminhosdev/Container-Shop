package br.com.lucas.Container.Store.service;

import br.com.lucas.Container.Store.entity.Cliente;
import br.com.lucas.Container.Store.repository.Cliente_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private Cliente_repository clienteRepository;

    public Cliente saving(Cliente cliente){
        return clienteRepository.save(cliente);
    }
    public List<Cliente> listingClientes(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> searchById(Long id){
        return clienteRepository.findById(id);
    }
    public void deletedById(Long id){
        clienteRepository.deleteById(id);
    }



}
